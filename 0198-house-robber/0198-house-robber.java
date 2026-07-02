class Solution {

    public int rob(int[] nums) {

        // If there is only one house
        if (nums.length == 1) {
            return nums[0];
        }

        int n = nums.length;

        // DP array to store maximum profit till each house
        int[] dp = new int[n];

        // Base cases
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        // Compute maximum profit for each house
        for (int i = 2; i < n; i++) {

            // Either skip current house or rob it
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        // Maximum money that can be robbed
        return dp[n - 1];
    }
}
