class Solution {
    public boolean isAnagram(String s, String t) {

    
        if (s.length() != t.length()) {
            return false;
        }

        // Convert strings to char arrays only once
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        int[] count = new int[26];

        // Traverse both arrays
        for (int i = 0; i < sArr.length; i++) {

        
            count[sArr[i] - 'a']++;
            count[tArr[i] - 'a']--;
        }

        // Verify all character frequencies are zero
        for (int num : count) {
            if (num != 0) {
                return false;
            }
        }

        return true;
    }
}