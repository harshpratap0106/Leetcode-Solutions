class Solution {
    public int[] sortedSquares(int[] nums) {

        int n = nums.length;

        // Result array
        int[] result = new int[n];

        // Two pointers
        int left = 0;
        int right = n - 1;

        // Fill result from last index
        for (int i = n - 1; i >= 0; i--) {

            // Compare absolute values
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {

                // Place larger square
                result[i] = nums[left] * nums[left];

                // Move left pointer
                left++;
            } else {

                // Place larger square
                result[i] = nums[right] * nums[right];

                // Move right pointer
                right--;
            }
        }

        return result;
    }
}