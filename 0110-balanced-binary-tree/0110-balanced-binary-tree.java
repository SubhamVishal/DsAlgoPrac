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
        return height(root).isBalanced;
    }
    public Pair height(TreeNode root){
        if(root == null){
            return new Pair(0, true);
        }
        Pair left = height(root.left);
        Pair right = height(root.right);
        if(!left.isBalanced || !right.isBalanced) {
            return new Pair(0, false);
        }
       return new Pair(Math.max(left.height, right.height) + 1, Math.abs(left.height - right.height) <= 1);
    }
    
    static class Pair {
        int height;
        boolean isBalanced;
        
        public Pair(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }
}