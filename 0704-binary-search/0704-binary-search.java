class Solution {
    public int search(int[] nums, int target) {

        // Start of search space
        int left = 0;

        // End of search space
        int right = nums.length - 1;

        // Continue while search space exists
        while (left <= right) {

            // Find middle index
            int mid = left + (right - left) / 2;

            // Target found
            if (nums[mid] == target) {
                return mid;
            }

            // Search right half
            else if (nums[mid] < target) {
                left = mid + 1;
            }

            // Search left half
            else {
                right = mid - 1;
            }
        }

        // Target not found
        return -1;
    }
}