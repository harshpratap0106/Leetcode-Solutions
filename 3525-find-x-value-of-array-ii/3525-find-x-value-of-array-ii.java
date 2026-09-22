class Solution {
    int n, k;
    int[] nums, prod;
    int[][] cnt;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.nums = nums;

        prod = new int[4 * n];
        cnt = new int[4 * n][k];

        build(1, 0, n - 1);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int idx = queries[q][0];
            nums[idx] = queries[q][1];

            update(1, 0, n-1, idx);

            int[] res = query(1, 0, n - 1, queries[q][2], n - 1);
            ans[q] = res[queries[q][3]];
        }

        return ans;
    }

    void build(int node, int l, int r) {
        if (l == r) {
            int rem = nums[l] % k;
            prod[node] = rem;
            cnt[node][rem] = 1;
            return;
        }

        int mid = (l + r) >>> 1;
        build(node << 1, l, mid);
        build(node << 1 | 1, mid + 1, r);
        merge(node, node << 1, node << 1 | 1);
    }

    void update(int node, int l, int r, int idx) {
        if (l == r) {
            int rem = nums[idx] % k;
            prod[node] = rem;

            for (int i = 0; i < k; i++)
                cnt[node][i] = 0;

            cnt[node][rem] = 1;
            return;
        }

        int mid = (l + r) >>> 1;

        if (idx <= mid)
            update(node << 1, l, mid, idx);
        else
            update(node << 1 | 1, mid + 1, r, idx);

        merge(node, node << 1, node << 1 | 1);
    }

    void merge(int node, int left, int right) {
        int p = prod[left];
        prod[node] = (int)((long) p * prod[right] % k);

        for (int i = 0; i < k; i++)
            cnt[node][i] = cnt[left][i];

        for (int i = 0; i < k; i++) {
            if (cnt[right][i] != 0) {
                int rem = (int)((long) p * i % k);
                cnt[node][rem] += cnt[right][i];
            }
        }
    }

    int[] query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr)
            return cnt[node];

        int mid = (l + r) >>> 1;

        if (qr <= mid)
            return query(node << 1, l, mid, ql, qr);

        if (ql > mid)
            return query(node << 1 | 1, mid + 1, r, ql, qr);

        int[] left = query(node << 1, l, mid, ql, qr);
        int[] right = query(node << 1 | 1, mid + 1, r, ql, qr);

        int leftProd = getProduct(node << 1, l, mid, ql, qr);

        int[] res = new int[k];

        for (int i = 0; i < k; i++)
            res[i] = left[i];

        for (int i = 0; i < k; i++) {
            int rem = (int)((long) leftProd * i % k);
            res[rem] += right[i];
        }

        return res;
    }

    int getProduct(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr)
            return prod[node];

        int mid = (l + r) >>> 1;

        if (qr <= mid)
            return getProduct(node << 1, l, mid, ql, qr);

        if (ql > mid)
            return getProduct(node << 1 | 1, mid + 1, r, ql, qr);

        return (int)((long)
                getProduct(node << 1, l, mid, ql, qr) *
                getProduct(node << 1 | 1, mid + 1, r, ql, qr) % k);
    }
}