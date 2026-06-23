class Solution {
    public char nextGreatestLetter(char[] letters, char target) {

        int left = 0;
        int right = letters.length - 1;

        char ans = letters[0];

        // Binary Search
        while (left <= right) {

            // Find middle index
            int mid = left + (right - left) / 2;

            // Found a character greater than target
            if (letters[mid] > target) {
                ans = letters[mid];
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        return ans;
    }
}