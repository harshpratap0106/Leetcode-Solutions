  class Solution {
    public int distinctSubseqII(String s) {
        int mod = 1000000007;
        long[] last = new long[26];
        long total = 0;

        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';
            long newTotal = (2*total+1-last[idx]+mod) % mod;
            last[idx] = total+1;
            total = newTotal;
        }
        return (int) total;
    }
}     