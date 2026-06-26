class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // Traverse the BST
        while (root != null) {

            // Both nodes are in the left subtree
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            }

            // Both nodes are in the right subtree
            else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            }

            // One node is on each side (or one is the current node)
            else {
                return root;
            }
        }

        return null;
    }
}