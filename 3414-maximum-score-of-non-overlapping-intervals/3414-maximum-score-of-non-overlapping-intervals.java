import java.util.*;

class Solution {
    int[][] a;
    int n;
    long[][] dp;
    List<Integer>[][] best;

    public int[] maximumWeight(List<List<Integer>> intervals) {
        n = intervals.size();
        a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        dp = new long[n + 1][5];
        best = new ArrayList[n + 1][5];

        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], -1);

        List<Integer> ans = solve(0, 4);

        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++)
            res[i] = ans.get(i);

        return res;
    }

    private List<Integer> solve(int i, int k) {
        if (i == n || k == 0)
            return new ArrayList<>();

        if (dp[i][k] != -1)
            return best[i][k];

        List<Integer> skip = solve(i + 1, k);
        long skipScore = getScore(i + 1, k);

        int next = findNext(i);
        List<Integer> take = new ArrayList<>(solve(next, k - 1));
        take.add(a[i][3]);
        Collections.sort(take);

        long takeScore = a[i][2] + getScore(next, k - 1);

        if (takeScore > skipScore) {
            dp[i][k] = takeScore;
            return best[i][k] = take;
        }
        if (takeScore < skipScore) {
            dp[i][k] = skipScore;
            return best[i][k] = skip;
        }
        dp[i][k] = takeScore;
        return best[i][k] = smaller(take, skip);
    }

    private long getScore(int i, int k) {
        if (i == n || k == 0)
            return 0;

        if (dp[i][k] == -1)
            solve(i, k);

        return dp[i][k];
    }

    private int findNext(int i) {
        int l = i+1;
        int r = n;

        while (l < r) {
            int mid = l + (r-l)/ 2;

            if (a[mid][0] > a[i][1])
                r = mid;
            else
                l = mid+1;
        }
        return l;
    }

    private List<Integer> smaller(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
            if (!a.get(i).equals(b.get(i)))
                return a.get(i) < b.get(i) ? a : b;
        }
        return a.size() <= b.size() ? a : b;
    }
}