package com.test.dsa.util.queue;

import java.util.*;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> outputList = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>();
        int left = 0;
        int right = 0;
        while(right < nums.length) {
            // pop smaller values from queue
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[right]) {
                deque.pollLast();
            }
            deque.addLast(right);

            while(!deque.isEmpty() && left > deque.peekFirst()) {
                deque.pollFirst();
            }

            if(!deque.isEmpty() && (right - left + 1) >= k) {
                outputList.add(nums[deque.peekFirst()]);
                left += 1;
            }
            right += 1;
        }
        return outputList.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] arr = {7, 2, 4};
        int k = 2;
        System.out.println(Arrays.toString(new SlidingWindowMaximum().maxSlidingWindow(arr, k)));
    }
}
