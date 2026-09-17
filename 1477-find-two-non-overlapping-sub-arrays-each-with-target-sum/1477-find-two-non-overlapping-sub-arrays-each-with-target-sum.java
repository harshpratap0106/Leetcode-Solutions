import java.util.*;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] best = new int[n + 1];
        Arrays.fill(best, Integer.MAX_VALUE);

        int left = 0;
        long sum = 0;
        int ans = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left++];
            }

            if (sum == target) {
                int len = right - left + 1;

                if (best[left] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, len + best[left]);
                }
                best[right + 1] = Math.min(best[right], len);
            } else {
                best[right + 1] = best[right];
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}