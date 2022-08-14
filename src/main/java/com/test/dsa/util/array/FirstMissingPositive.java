package com.test.dsa.util.array;

public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] < 0) {
                nums[i] = 0;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            int value = Math.abs(nums[i]);
            if((1 <= value) && (value <= nums.length)) {
                if(nums[value - 1] > 0) {
                    nums[value - 1] *= -1;
                } else if(nums[value - 1] == 0) {
                    nums[value - 1] = -1 * (nums.length + 1);
                }
            }
        }

        for(int i = 1; i < nums.length + 1; i++) {
            if(nums[i - 1] >= 0) {
                return i;
            }
        }
        return nums.length + 1;
    }
}
