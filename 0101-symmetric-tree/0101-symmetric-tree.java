class Solution {

    public boolean isSymmetric(TreeNode root) {

        // Compare left and right subtree
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {

        // Both nodes are null
        if (left == null && right == null) {
            return true;
        }

        // One node is null
        if (left == null || right == null) {
            return false;
        }

        // Values are different
        if (left.val != right.val) {
            return false;
        }

        // Check mirror condition
        return isMirror(left.left, right.right)
                && isMirror(left.right, right.left);
    }
}