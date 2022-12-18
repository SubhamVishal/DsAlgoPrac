package com.test.dsa.util.dp;

import java.util.Arrays;

public class TrappingRainWater {
    public int trap(int[] height) {
        int[] nextGreaterToTheLeft = new int[height.length];
        int[] nextGreaterToTheRight = new int[height.length];
        Arrays.fill(nextGreaterToTheLeft, -1);
        Arrays.fill(nextGreaterToTheRight, -1);
        int maxGoingLeft = height[0];
        for (int i = 1; i < height.length; i++) {
            if (maxGoingLeft > height[i]) {
                nextGreaterToTheLeft[i] = maxGoingLeft;
            } else {
                maxGoingLeft = height[i];
            }
        }
        int maxGoingRight = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            if (maxGoingRight > height[i]) {
                nextGreaterToTheRight[i] = maxGoingRight;
            } else {
                maxGoingRight = height[i];
            }
        }
        int totalWater = 0;
        for (int i = 1; i < height.length - 1; i++) {
            if (!(nextGreaterToTheLeft[i] == -1) && !(nextGreaterToTheRight[i] == -1)) {
                int currentLimit = Math.min(nextGreaterToTheLeft[i], nextGreaterToTheRight[i]);
                int water = currentLimit - height[i];
                if (water > 0) {
                    totalWater += water;
                }
            }
        }
        return totalWater;
    }

    public static void main(String[] args) {
        TrappingRainWater trappingRainWater = new TrappingRainWater();
        System.out.println(trappingRainWater.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}
