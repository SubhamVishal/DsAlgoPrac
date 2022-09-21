package com.test.dsa.util.binarysearchtree;

import java.util.*;
import java.util.stream.Collectors;

public class RightSmallerThan {

    public static List<Integer> rightSmallerThan(List<Integer> array) {
        if (array == null || array.size() == 0) {
            return new ArrayList<>();
        }
        // elements greater than or equal to current are stored here
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        // elements lesser than current are stored here
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        int n = array.size();
        ArrayList<Integer> res = new ArrayList<>();
        res.add(0, 0);
        minQueue.add(array.get(n - 1));
        for (int i = n - 2; i >= 0; i--) {
            boolean flag = false;
            int curr = array.get(i);
            while (!minQueue.isEmpty() && minQueue.peek() < curr) {
                maxQueue.add(minQueue.poll());
            }
            while (!maxQueue.isEmpty() && maxQueue.peek() >= curr) {
                minQueue.add(maxQueue.poll());
            }
            minQueue.add(curr);
            res.add(0, maxQueue.size());
        }
        return res;
    }

    public List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();

        List<Integer> rightSmallerCounts = new ArrayList<>();
        Arrays.stream(nums).forEach(rightSmallerCounts::add);
        int lastIdx = nums.length - 1;
        SpecialBST bst = new SpecialBST(nums[lastIdx]);
        rightSmallerCounts.set(lastIdx, 0);
        for (int i = nums.length - 2; i >= 0; i--) {
            bst.insert(nums[i], i, rightSmallerCounts);
        }
        return rightSmallerCounts;
    }

    static class SpecialBST {
        public int value;
        public int leftSubtreeSize;
        public SpecialBST left;
        public SpecialBST right;

        public SpecialBST(int value) {
            this.value = value;
            leftSubtreeSize = 0;
            left = null;
            right = null;
        }

        public void insert(int value, int idx, List<Integer> rightSmallerCounts) {
            insertHelper(value, idx, rightSmallerCounts, 0);
        }

        private void insertHelper(int value, int idx, List<Integer> rightSmallerCounts, int numSmallerAtInsertTime) {
            if (value < this.value) {
                leftSubtreeSize++;
                if (left == null) {
                    left = new SpecialBST(value);
                    rightSmallerCounts.set(idx, numSmallerAtInsertTime);
                } else {
                    left.insertHelper(value, idx, rightSmallerCounts, numSmallerAtInsertTime);
                }
            } else {
                numSmallerAtInsertTime += leftSubtreeSize;
                if (value > this.value) {
                    numSmallerAtInsertTime++;
                }
                if (right == null) {
                    right = new SpecialBST(value);
                    rightSmallerCounts.set(idx, numSmallerAtInsertTime);
                } else {
                    right.insertHelper(value, idx, rightSmallerCounts, numSmallerAtInsertTime);
                }
            }
        }
    }
}
