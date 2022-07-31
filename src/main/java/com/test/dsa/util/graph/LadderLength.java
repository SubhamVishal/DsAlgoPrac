package com.test.dsa.util.graph;

import java.util.*;

//https://leetcode.com/problems/word-ladder
public class LadderLength {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        if (!wordList.contains(beginWord)) {
            wordList.add(beginWord);
        }
        Map<String, List<String>> neighborMap = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String pattern = word.substring(0, i) + "*" + word.substring(i + 1);
                neighborMap.putIfAbsent(pattern, new ArrayList<>());
                neighborMap.get(pattern).add(word);
            }
        }
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        Deque<String> dq = new LinkedList<>();
        dq.addLast(beginWord);
        int result = 1;
        while (!dq.isEmpty()) {
            int size = dq.size();
            for (int i = 0; i < size; i++) {
                String word = dq.pollFirst();
                if (null == word) {
                    continue;
                }
                if (endWord.equals(word)) {
                    return result;
                }
                for (int j = 0; j < word.length(); j++) {
                    String pattern = word.substring(0, j) + "*" + word.substring(j + 1);
                    for (String neighbor : neighborMap.get(pattern)) {
                        if (!visited.contains(neighbor)) {
                            visited.add(neighbor);
                            dq.addLast(neighbor);
                        }
                    }
                }
            }
            result += 1;
        }
        return 0;
    }
}
