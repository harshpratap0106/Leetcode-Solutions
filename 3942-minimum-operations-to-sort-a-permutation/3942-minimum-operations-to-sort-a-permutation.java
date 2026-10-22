class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;

        while (nums[zero] != 0) {
            zero++;
        }
        boolean normal = true;
        for (int i = 0; i<n; i++) {
            if (nums[(i+zero) % n] != i) {
                normal = false;
                break;
            }
        }
        boolean reversed = true;
        int k = n-1-zero;

        for (int i = 0; i<n; i++) {
            if (nums[n-1 - ((i+k)%n)] != i) {
                reversed = false;
                break;
            }
        }
        int ans = Integer.MAX_VALUE;
        if (normal){
            ans = Math.min(ans, Math.min(zero, n-zero+2));
        }
        if (reversed){
            ans = Math.min(ans, Math.min(k + 1, n-k+1));
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}