package com.test.dsa.util.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxPathSumInBinaryTree {
    /**
     * Write a function that takes in a binary tree and returns its
     * max path sum.
     * tree:        1
     * 2   3
     * 4  5 6  7
     * <p>
     * 18 // 5 + 2 + 1 + 3 + 7
     */
    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    public static int maxPathSum(BinaryTree tree) {
        List<Integer> maxSumArray = findMaxSum(tree);
        return maxSumArray.get(1);
    }

    public static List<Integer> findMaxSum(BinaryTree tree) {
        if (tree == null) {
            return new ArrayList<>(Arrays.asList(0, Integer.MIN_VALUE));
        }
        List<Integer> leftMaxSumArray = findMaxSum(tree.left);
        Integer leftMaxSumAsBranch = leftMaxSumArray.get(0);
        Integer leftMaxPathSum = leftMaxSumArray.get(1);

        List<Integer> rightMaxSumArray = findMaxSum(tree.right);
        Integer rightMaxSumAsBranch = rightMaxSumArray.get(0);
        Integer rightMaxPathSum = rightMaxSumArray.get(1);

        int maxSumAsBranch = Math.max(Math.max(leftMaxSumAsBranch, rightMaxSumAsBranch) + tree.value, tree.value);
        int maxSumAsRootNode = Math.max(leftMaxSumAsBranch + tree.value + rightMaxSumAsBranch, maxSumAsBranch);
        int maxPathSum = Math.max(Math.max(leftMaxPathSum, rightMaxPathSum), maxSumAsRootNode);
        return new ArrayList<>(Arrays.asList(maxSumAsBranch, maxPathSum));
    }
}
