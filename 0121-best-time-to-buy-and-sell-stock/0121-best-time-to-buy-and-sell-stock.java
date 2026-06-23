class Solution {
    public int maxProfit(int[] prices) {

        // Minimum buying price seen so far
        int buy = prices[0];

        // Maximum profit
        int maxProfit = 0;

        // Traverse stock prices
        for (int i = 1; i < prices.length; i++) {

            // Found a cheaper buying price
            if (prices[i] < buy) {
                buy = prices[i];
            }

            // Calculate profit if sold today
            int profit = prices[i] - buy;

            maxProfit = Math.max(maxProfit, profit);
        }

        return maxProfit;
    }
}