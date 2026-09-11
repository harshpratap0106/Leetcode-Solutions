class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];

        for (int d : digits)
            freq[d]++;

        int ans = 0;

        for (int a = 1; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                for (int c = 0; c <= 8; c += 2) {
                    if (freq[a]==0 || freq[b]==0 || freq[c]==0)
                        continue;

                    int[] used = new int[10];
                    used[a]++;
                    used[b]++;
                    used[c]++;

                    boolean possible = true;
                    for (int d = 0; d <= 9; d++) {
                        if (used[d] > freq[d]) {
                            possible = false;
                        
                        }
                    }
                    if (possible)
                        ans++;
                }
            }
        }
        return ans;
    }
}