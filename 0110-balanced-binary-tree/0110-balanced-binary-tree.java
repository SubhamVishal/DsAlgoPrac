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
    boolean check=true;
    int checker(TreeNode root){
        if(root==null) return -1;//this when added with 1 gives 0 the actual height of null
        int left,right;
        left=checker(root.left)+1;//find out the height of the node and add the distance between the node and present node which is one
        right=checker(root.right)+1;//do the same
        if(Math.abs(left-right)>1) {//break if the difference is greater than 1
            check=false;
            return (int)-1;
        }
        return Math.max(left,right);//return the maximum of the 2 heights
    }
    public boolean isBalanced(TreeNode root) {
        checker(root);
        return check;
    }
}