import java.util.*;

class Solution {
    static class State {
        int r;
        int c;
        int energy;
        int mask;

        State(int r, int c, int energy, int mask) {
            this.r = r;
            this.c = c;
            this.energy = energy;
            this.mask = mask;
        }
    }

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startR = 0;
        int startC = 0;

        int[][] litterId = new int[m][n];

        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int litterCount = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                char ch = classroom[r].charAt(c);

                if (ch == 'S') {
                    startR = r;
                    startC = c;
                }

                if (ch == 'L') {
                    litterId[r][c] = litterCount;
                    litterCount++;
                }
            }
        }

        if (litterCount == 0) {
            return 0;
        }

        int allMask = (1 << litterCount)-1;
        boolean[][][] visited =
                new boolean[m*n][energy+1][1 << litterCount];

        Queue<State> queue = new ArrayDeque<>();

        queue.offer(new State(startR, startC, energy, 0));
        visited[startR * n + startC][energy][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                State current = queue.poll();

                int r = current.r;
                int c = current.c;
                int currentEnergy = current.energy;
                int mask = current.mask;

                for (int d = 0; d < 4; d++) {

                    if (currentEnergy == 0) {
                        continue;
                    }

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n) {
                        continue;
                    }
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    int newEnergy = currentEnergy - 1;
                    int newMask = mask;

                    if (classroom[nr].charAt(nc) == 'L') {
                        int id = litterId[nr][nc];
                        newMask = mask | (1 << id);
                    }
                    if (classroom[nr].charAt(nc) == 'R') {
                        newEnergy = energy;
                    }
                    if (newMask == allMask) {
                        return moves + 1;
                    }
                    int position = nr * n + nc;

                    if (visited[position][newEnergy][newMask]) {
                        continue;
                    }
                    visited[position][newEnergy][newMask] = true;

                    queue.offer(
                        new State(nr, nc, newEnergy, newMask)
                    );
                }
            }

            moves++;
        }

        return -1;
    }
}