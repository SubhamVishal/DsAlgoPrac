package com.test.dsa.util.stack;

import java.util.Stack;

//https://leetcode.com/problems/remove-k-digits
public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        for (char c : num.toCharArray()) {
            while (k > 0 && !stack.isEmpty() && stack.peek() > c) {
                k -= 1;
                stack.pop();
            }
            stack.add(c);
        }
        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        String result = sb.toString();
        return removeLeadingZeroes(result);
    }

    private String removeLeadingZeroes(String str) {
        StringBuilder finalResult = new StringBuilder();
        boolean leadingZeroesEnd = false;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if(!leadingZeroesEnd && str.charAt(i) == '0') {
                continue;
            } else {
                leadingZeroesEnd = true;
                finalResult.append(str.charAt(i));
            }
        }
        if(finalResult.toString().equals("")) {
            return "0";
        }
        return finalResult.toString();
    }
}
