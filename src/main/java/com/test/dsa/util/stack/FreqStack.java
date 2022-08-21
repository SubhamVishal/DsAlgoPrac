package com.test.dsa.util.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class FreqStack {
    Map<Integer, Integer> countMap;
    int maxCount = 0;
    Map<Integer, Stack<Integer>> stacks;

    public FreqStack() {
        this.countMap = new HashMap<>();
        this.stacks = new HashMap<>();
    }

    public void push(int val) {
        int valCount = 1 + this.countMap.getOrDefault(val, 0);
        countMap.put(val, valCount);
        if (valCount > this.maxCount) {
            this.maxCount = valCount;
            this.stacks.put(valCount, new Stack<>());
        }
        this.stacks.get(valCount).add(val);
    }

    public int pop() {
        int res = this.stacks.get(this.maxCount).pop();
        this.countMap.put(res, this.countMap.get(res) - 1);
        if (this.stacks.get(this.maxCount).isEmpty()) {
            this.maxCount -= 1;
        }
        return res;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */