/**
 * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
 * 内存消耗：38.2 MB, 在所有 Java 提交中击败了84.37%的用户
 *
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
    public int maxDepth(TreeNode root) {
        return helper(root, 0);
    }
    private int helper(TreeNode node, int layer) {
        if (node != null) {
            int left = helper(node.left, layer + 1);
            int right = helper(node.right, layer + 1);
            return left > right ? left : right;
        } else {
            return layer;
        }
    }
}
