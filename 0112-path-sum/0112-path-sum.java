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

    public boolean hasPathSum(TreeNode root, int targetSum) {

        // Empty tree
        if (root == null) {
            return false;
        }

        // Subtract current node value
        targetSum -= root.val;

        // If leaf node, check remaining sum
        if (root.left == null && root.right == null) {
            return targetSum == 0;
        }

        // Search in left or right subtree
        return hasPathSum(root.left, targetSum) ||
               hasPathSum(root.right, targetSum);
    }
}