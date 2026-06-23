class Solution {
    public boolean isSubsequence(String s, String t) {

        // Pointers for string s and t
        int i = 0;
        int j = 0;

        // Traverse string t
        while (i < s.length() && j < t.length()) {

            // If characters match, move pointer of s
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            // Always move pointer of t
            j++;
        }

        // If all characters of s were found
        return i == s.length();
    }
}