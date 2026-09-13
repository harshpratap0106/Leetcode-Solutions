class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int NolargestOverlap = 0;

        for (int rm = -n + 1; rm<n; rm++) {  // rm is row movement up and down
            for (int cm = -n + 1; cm<n; cm++) {  // cm is col movement right and left
                int count = 0;

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        int x = i + rm;
                        int y = j + cm;

                        if (x>=0 && x<n && y>=0 && y<n && img1[i][j]==1 && img2[x][y] == 1) {
                            count++;
                        }
                    }
                }
                NolargestOverlap = Math.max(NolargestOverlap,count);
            }
        }        
        return NolargestOverlap;
    }
}