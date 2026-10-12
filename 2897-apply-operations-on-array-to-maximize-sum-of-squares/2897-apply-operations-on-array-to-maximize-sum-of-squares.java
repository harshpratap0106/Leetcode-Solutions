class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bits = new int[31];
        long mod = 1_000_000_007L;

        for (int num : nums) {
            while (num>0) {
                int b = Integer.numberOfTrailingZeros(num);
                bits[b]++;
                num &= num-1;
            }
        }

        long ans = 0;

        for (int i = 0; i<k; i++) {
            long val = 0;
            for (int b = 30; b>=0; b--) {
                if (bits[b]>0) {
                    val |= 1L<<b;
                    bits[b]--;
                }
            }
            ans = (ans+val*val)%mod;
        }
        return (int)ans;
    }
}