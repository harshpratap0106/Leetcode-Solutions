class Solution {
    public int reverseDegree(String s) {
        int res = 0;

        for (int i = 0; i<s.length(); i++) {
            int value = 26-(s.charAt(i)-'a');
            int pos = i+1;
            res += value*pos;
        }
        return res;
    }
}