class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {

        int[] count = new int[26];

        // Count each character in magazine
        for (char ch : magazine.toCharArray()) {
            count[ch - 'a']++;
        }

        // Try to build ransomNote
        for (char ch : ransomNote.toCharArray()) {

            count[ch - 'a']--;

            if (count[ch - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}