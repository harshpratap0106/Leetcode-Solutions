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

    // Counts how many nodes have been visited
    private int count = 0;

    // Stores the kth smallest value
    private int answer = 0;

    public int kthSmallest(TreeNode root, int k) {

        inorder(root, k);

        return answer;
    }

    // Inorder Traversal
    private void inorder(TreeNode root, int k) {

        if (root == null) {
            return;
        }

        // Visit left subtree
        inorder(root.left, k);

        // Visit current node
        count++;

        // If current node is kth smallest
        if (count == k) {
            answer = root.val;
            return;
        }

        // Visit right subtree
        inorder(root.right, k);
    }
}