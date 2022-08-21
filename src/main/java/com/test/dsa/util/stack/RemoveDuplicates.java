package com.test.dsa.util.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class RemoveDuplicates {

    public String removeDuplicates(String s, int k) {
        Stack<Map<Character, Integer>> stack = new Stack<>();
        for(Character c : s.toCharArray()) {
            if(!stack.isEmpty() && stack.peek().containsKey(c)) {
                stack.peek().put(c, stack.peek().get(c) + 1);
            } else {
                Map<Character, Integer> pair = new HashMap<>();
                pair.put(c, 1);
                stack.add(pair);
            }
            if(stack.peek().get(c) == k) {
                stack.pop();
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            Map<Character, Integer> currChar = stack.pop();
            Set<Character> characterSet = currChar.keySet();
            for(Character ch : characterSet) {
                for(int i = 0; i < currChar.get(ch); i++) {
                    sb.append(ch);
                }
            }
        }
        return sb.reverse().toString();
    }
}
