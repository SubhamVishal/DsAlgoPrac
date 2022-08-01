package com.test.dsa.util.graph;

import java.util.*;
import java.util.stream.Collectors;
//https://leetcode.com/discuss/interview-question/248131/microsoft-interview-round-1-alien-dictionary
/*
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
"wrt",
"wrf",
"er",
"ett",
"rftt"
]
The correct order is: "wertf".
 */
public class AlienOrder {
    public String alienOrder(List<String> words) {
        Map<Character, List<Character>> adjMap = new HashMap<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                adjMap.putIfAbsent(word.charAt(i), new ArrayList<>());
            }
        }
        for (int i = 0; i < words.size() - 1; i++) {
            String word1 = words.get(i);
            String word2 = words.get(i + 1);
            int minLength = Math.min(word1.length(), word2.length());
            if (word1.length() > word2.length() && word1.substring(0, minLength).equals(word2.substring(0, minLength))) {
                return "";
            }
            for (int j = 0; j < minLength; j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    adjMap.get(word1.charAt(j)).add(word2.charAt(j));
                    break;
                }
            }
        }
        Map<Character, Boolean> visit = new HashMap<>();
        List<Character> result = new ArrayList<>();
        for (Character c : adjMap.keySet()) {
            if (dfs(c, adjMap, visit, result)) {
                return "";
            }
        }
        Collections.reverse(result);
        return result.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private boolean dfs(Character c, Map<Character, List<Character>> adjMap, Map<Character, Boolean> visited, List<Character> result) {
        if (visited.containsKey(c)) {
            return visited.get(c);
        }
        visited.put(c, true);
        for (Character neighbor : adjMap.getOrDefault(c, new ArrayList<>())) {
            if (dfs(neighbor, adjMap, visited, result)) {
                return true;
            }
        }
        visited.put(c, false);
        result.add(c);
        return false;
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList("wrt", "wrf", "er", "ett", "rftt");
        String result = new AlienOrder().alienOrder(words);
        System.out.println("result : " + result);
        System.out.println("wertf".equals(result));
    }
}
