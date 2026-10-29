
import java.util.*;

class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        int[] parent = new int[n];
        int[] depth = new int[n];
        int[] size = new int[n];
        int[] heavy = new int[n];
        int[] head = new int[n];
        int[] pos = new int[n];

        Arrays.fill(parent, -2);
        Arrays.fill(heavy, -1);

        int[] order = new int[n];
        int[] stack = new int[n];
        int top = 0, count = 0;

        stack[top++] = 0;
        parent[0] = -1;

        while (top > 0) {
            int u = stack[--top];
            order[count++] = u;

            for (int v : adj[u]) {
                if (v == parent[u]) continue;
                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            size[u] = 1;
            int maxSize = 0;

            for (int v : adj[u]) {
                if (parent[v] == u) {
                    size[u] += size[v];
                    if (size[v] > maxSize) {
                        maxSize = size[v];
                        heavy[u] = v;
                    }
                }
            }
        }

        int[] starts = new int[n];
        int[] chainHeads = new int[n];
        top = 0;
        int timer = 0;

        starts[top] = 0;
        chainHeads[top++] = 0;

        while (top > 0) {
            int start = starts[--top];
            int h = chainHeads[top];

            for (int u = start; u != -1; u = heavy[u]) {
                head[u] = h;
                pos[u] = timer++;

                for (int v : adj[u]) {
                    if (parent[v] == u && v != heavy[u]) {
                        starts[top] = v;
                        chainHeads[top++] = v;
                    }
                }
            }
        }

        int[] values = new int[n];
        for (int u = 0; u < n; u++) {
            values[pos[u]] = 1 << (s.charAt(u) - 'a');
        }

        SegTree seg = new SegTree(values);

        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {
            String[] p = query.split(" ");
            int u = Integer.parseInt(p[1]);

            if (p[0].equals("update")) {
                int newMask = 1 << (p[2].charAt(0) - 'a');
                seg.update(pos[u], newMask);
            } else {
                int v = Integer.parseInt(p[2]);
                int mask = 0;

                while (head[u] != head[v]) {
                    if (depth[head[u]] < depth[head[v]]) {
                        int temp = u;
                        u = v;
                        v = temp;
                    }

                    mask ^= seg.query(pos[head[u]], pos[u]);
                    u = parent[head[u]];
                }

                if (depth[u] > depth[v]) {
                    int temp = u;
                    u = v;
                    v = temp;
                }

                mask ^= seg.query(pos[u], pos[v]);
                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    static class SegTree {
        int size;
        int[] tree;

        SegTree(int[] values) {
            size = 1;
            while (size < values.length) size <<= 1;
            tree = new int[size * 2];

            for (int i = 0; i < values.length; i++) {
                tree[size + i] = values[i];
            }

            for (int i = size - 1; i > 0; i--) {
                tree[i] = tree[i * 2] ^ tree[i * 2 + 1];
            }
        }

        void update(int index, int value) {
            int i = size + index;
            tree[i] = value;

            while (i > 1) {
                i >>= 1;
                tree[i] = tree[i * 2] ^ tree[i * 2 + 1];
            }
        }

        int query(int left, int right) {
            int result = 0;
            left += size;
            right += size;

            while (left <= right) {
                if ((left & 1) == 1) result ^= tree[left++];
                if ((right & 1) == 0) result ^= tree[right--];

                left >>= 1;
                right >>= 1;
            }

            return result;
        }
    }
}
