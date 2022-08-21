package com.test.dsa.util.stack;

import java.util.Stack;

public class Find132Pattern {
    public boolean find132pattern(int[] nums) {
        Stack<Integer[]> stack = new Stack<>(); // pair [num, minLeft], mono decreasing
        int curMin = nums[0];
        for (int n : nums) {
            while (!stack.isEmpty() && n >= stack.peek()[0]) {
                stack.pop();
            }
            if (!stack.isEmpty() && n > stack.peek()[1]) {
                return true;
            }
            stack.add(new Integer[]{n, curMin});
            curMin = Math.min(curMin, n);
        }
        return false;
    }
}
