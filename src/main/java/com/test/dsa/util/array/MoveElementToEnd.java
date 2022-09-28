package com.test.dsa.util.array;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MoveElementToEnd {
    public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
        int i = 0;
        int j = array.size() - 1;
        while (i < j) {
            while (i < j && array.get(j) == toMove) {
                j--;
            }
            if (array.get(i) == toMove) {
                swap(array, i, j);
            }
            i++;
        }
        return array;
    }

    private static void swap(List<Integer> x, int a, int b) {
        Integer t = x.get(a);
        x.set(a, x.get(b));
        x.set(b, t);
    }

    public static int minCost(String s, int[] cost) {
        if(s.length() != cost.length) {
            return 0;
        }
        if(s.length() == 0 || s.length() == 1) {
            return 0;
        }
        int total_cost = 0;
        char prev_char = s.charAt(0);
        int max_cost = cost[0];
        int cur_total_cost = cost[0];
        int length = s.length();
        for (int i = 1; i < length; i++) {
            char curr_char = s.charAt(i);
            int cur_cost = cost[i];
            if (curr_char == prev_char) {
                max_cost = Math.max(max_cost, cur_cost);
                cur_total_cost += cur_cost;
            } else {
                total_cost += cur_total_cost - max_cost;
                prev_char = curr_char;
                max_cost = cur_cost;
                cur_total_cost = cur_cost;
            }
        }
        total_cost += cur_total_cost - max_cost;
        return total_cost;
    }

    public static int solution(int[] A, int[] B) {
        int[] min_part = null;
        int[] max_part = null;
        int diff = getSumOfArray(A) - getSumOfArray(B);
        if(diff == 0) {
            return 0;
        } else if(diff < 0) {
            min_part = A;
            max_part = B;
        } else {
            min_part = B;
            max_part = A;
        }
        diff = Math.abs(diff);
        PriorityQueue<Integer> min_heap = new PriorityQueue<>(min_part.length, Comparator.comparingInt(x -> x));
        for (int j : min_part) {
            min_heap.add(j);
        }
        PriorityQueue<Integer> max_heap = new PriorityQueue<>(max_part.length, (x, y) -> y - x);
        for (int j : max_part) {
            max_heap.add(j);
        }

        int count = 0;
        int val = 0;

        while(val < diff && !min_heap.isEmpty() && !max_heap.isEmpty()) {
            int val_from_min_heap = min_heap.peek();
            int val_from_max_heap = max_heap.peek();
            if(val_from_min_heap == 6 && val_from_max_heap == 1) {
                return -1;
            }

            int diff_if_min = 6 - val_from_min_heap;
            int diff_if_max = val_from_max_heap - 1;
            count += 1;
            val += Math.max(diff_if_min, diff_if_max);
            if(diff_if_min > diff_if_max) {
                min_heap.poll();
                min_heap.add(6);
            } else {
                max_heap.poll();
                max_heap.add(-1);
            }
        }
        return count;
    }

    private static int getSumOfArray(int[] arr) {
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }
        return sum;
    }



    public static void main(String[] args) {
        /*System.out.println(MoveElementToEnd.minCost("abccbd", new int[]{0, 1, 2, 3, 4, 5}));
        System.out.println(MoveElementToEnd.minCost("aabbcc", new int[]{1, 2, 1, 2, 1, 2}));
        System.out.println(MoveElementToEnd.minCost("aaaa", new int[]{3, 4, 5, 6}));
        System.out.println(MoveElementToEnd.minCost("ababa", new int[]{10, 5, 10, 5, 10}));*/
        System.out.println(MoveElementToEnd.solution(new int[]{5}, new int[]{1, 1, 6}));
        System.out.println(MoveElementToEnd.solution(new int[]{2, 3, 1, 1 ,2}, new int[]{5, 4, 6}));
        System.out.println(MoveElementToEnd.solution(new int[]{5, 4, 1, 2, 6, 5}, new int[]{2}));
        System.out.println(MoveElementToEnd.solution(new int[]{1, 2, 3, 4, 3, 2, 1}, new int[]{6}));
    }
}
