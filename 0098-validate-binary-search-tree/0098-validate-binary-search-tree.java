class Solution {

    public boolean isValidBST(TreeNode root) {

        // Start with the entire valid range
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // Helper function to validate BST
    private boolean validate(TreeNode root, long min, long max) {

        // Empty tree is always valid
        if (root == null) {
            return true;
        }

        // Current node must lie within (min, max)
        if (root.val <= min || root.val >= max) {
            return false;
        }

        // Validate left and right subtrees
        return validate(root.left, min, root.val) &&
               validate(root.right, root.val, max);
    }
}