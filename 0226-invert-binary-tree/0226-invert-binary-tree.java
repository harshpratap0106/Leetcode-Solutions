class Solution {
    public TreeNode invertTree(TreeNode root) {

        // Base case: empty tree
        if (root == null) {
            return null;
        }

        // Swap left and right child
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Invert left subtree
        invertTree(root.left);

        // Invert right subtree
        invertTree(root.right);
        
        return root;
    }
}