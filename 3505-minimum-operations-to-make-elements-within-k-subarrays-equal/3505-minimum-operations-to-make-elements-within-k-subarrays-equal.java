class Solution {
    private int m;
    private long[] cnt, sm;

    private void upd(int i, int c, long s) {
        for (; i <= m; i += i & -i) {
            cnt[i] += c;
            sm[i] += s;
        }
    }

    // smallest position whose prefix count >= kk
    private int kth(long kk) {
        int pos = 0;
        for (int p = Integer.highestOneBit(m); p > 0; p >>= 1) {
            int nxt = pos + p;
            if (nxt <= m && cnt[nxt] < kk) {
                pos = nxt;
                kk -= cnt[nxt];
            }
        }
        return pos + 1;
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        // coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int[] vals = new int[n];
        m = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) vals[m++] = sorted[i];
        }
        cnt = new long[m + 1];
        sm = new long[m + 1];

        // cost of each window via sliding median
        long[] cost = new long[n - x + 1];
        long S = 0;
        for (int i = 0; i < n; i++) {
            upd(Arrays.binarySearch(vals, 0, m, nums[i]) + 1, 1, nums[i]);
            S += nums[i];
            if (i >= x) {
                int old = nums[i - x];
                upd(Arrays.binarySearch(vals, 0, m, old) + 1, -1, -old);
                S -= old;
            }
            if (i >= x - 1) {
                int pos = kth((x + 1) / 2);
                long med = vals[pos - 1];

                // prefix count and sum up to pos
                long c = 0, s = 0;
                for (int p = pos; p > 0; p -= p & -p) {
                    c += cnt[p];
                    s += sm[p];
                }
                cost[i - x + 1] = med * c - s + (S - s) - med * (x - c);
            }
        }

        // dp over number of chosen windows
        final long INF = Long.MAX_VALUE / 2;
        long[] prev = new long[n + 1]; // dp[0][*] = 0
        for (int j = 1; j <= k; j++) {
            long[] cur = new long[n + 1];
            Arrays.fill(cur, INF);
            for (int i = x; i <= n; i++) {
                cur[i] = Math.min(cur[i - 1], prev[i - x] + cost[i - x]);
            }
            prev = cur;
        }
        return prev[n];
    }
}