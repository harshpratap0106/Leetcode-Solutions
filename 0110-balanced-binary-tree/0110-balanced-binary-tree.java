/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    public boolean isBalanced(TreeNode root) {

        // Tree is balanced if height function
        // does not return -1
        return height(root) != -1;
    }

    private int height(TreeNode root) {

        // Empty tree has height 0
        if (root == null) {
            return 0;
        }

        // Find height of left subtree
        int leftHeight = height(root.left);

        // Left subtree already unbalanced
        if (leftHeight == -1) {
            return -1;
        }

        // Find height of right subtree
        int rightHeight = height(root.right);

        if (rightHeight == -1) {
            return -1;
        }

        // Check balance condition
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return 1 + Math.max(leftHeight, rightHeight);
    }
}