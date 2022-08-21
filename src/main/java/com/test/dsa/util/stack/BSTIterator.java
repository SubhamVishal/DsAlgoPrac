package com.test.dsa.util.stack;

import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
//https://leetcode.com/problems/binary-search-tree-iterator
public class BSTIterator {
    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        if (root != null) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
        }
    }

    public int next() {
        TreeNode next = stack.pop();
        if (next.right != null) {
            TreeNode right = next.right;
            while (right != null) {
                stack.add(right);
                right = right.left;
            }
        }
        return next.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */