class Solution {

    public int lengthOfLongestSubstring(String s) {

        // Stores unique characters in the current window
        HashSet<Character> set = new HashSet<>();

        int left = 0;
        int maxLength = 0;

        // Expand the window
        for (int right = 0; right < s.length(); right++) {

            // Remove characters until duplicate is removed
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }

            // Add current character
            set.add(s.charAt(right));

            // Update maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}