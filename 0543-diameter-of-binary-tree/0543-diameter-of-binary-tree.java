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

    // Stores maximum diameter found so far
    private int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {

        // Calculate heights and update diameter
        height(root);

        return diameter;
    }

    private int height(TreeNode root) {

        // Base case
        if (root == null) {
            return 0;
        }

        // Height of left subtree
        int leftHeight = height(root.left);

        // Height of right subtree
        int rightHeight = height(root.right);

        // Update diameter
        diameter = Math.max(diameter,
                            leftHeight + rightHeight);

        // Return current height
        return 1 + Math.max(leftHeight, rightHeight);
    }
}