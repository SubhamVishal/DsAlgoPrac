package com.test.dsa.util.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElement {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> numIndexMap = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            numIndexMap.put(nums1[i], i);
        }
        int[] result = new int[nums1.length];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();
        for (int cur : nums2) {
            while (!stack.isEmpty() && cur > stack.peek()) {
                int val = stack.pop();
                int index = numIndexMap.get(val);
                result[index] = cur;
            }
            if (numIndexMap.containsKey(cur)) {
                stack.add(cur);
            }
        }
        return result;
    }
}
