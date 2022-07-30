package com.test.dsa.util.dp;

/*
Given list of strings, write a function that returns the longest string chain
that can be built from those strings.
strings = ["abde", "abc", "abd", abcde", "ade", "ae", "labde", "abcdef"]
output = ["abcdef", "abcde", "abde", "ade", "ae"]
 */

import java.util.*;

public class LongestStringChain {
    public static class StringChain {
        String nextString;
        Integer maxChainLength;

        public StringChain(String nextString, Integer maxChainLength) {
            this.nextString = nextString;
            this.maxChainLength = maxChainLength;
        }
    }
    // O(n * m^2 + nlog(n)) time | O(nm) space where n is the number of strings
    // and m is the length of the longest string
    public static List<String> longestStringChain(List<String> strings) {
        Map<String, StringChain> stringChains = new HashMap<>();
        for (String string : strings) {
            stringChains.put(string, new StringChain("", 1));
        }

        List<String> sortedStrings = new ArrayList<>(strings);
        sortedStrings.sort(Comparator.comparingInt(String::length));

        for (String string : sortedStrings) {
            findLongestStringChain(string, stringChains);
        }
        return buildLongestStringChain(strings, stringChains);
    }

    private static List<String> buildLongestStringChain(List<String> strings, Map<String, StringChain> stringChains) {
        int maxChainLength = 0;
        String chainStartingString = "";
        for (String string : strings) {
            if (stringChains.get(string).maxChainLength > maxChainLength) {
                maxChainLength = stringChains.get(string).maxChainLength;
                chainStartingString = string;
            }
        }

        List<String> ourLongestStringChain = new ArrayList<>();
        String currentString = chainStartingString;
        while (currentString != "") {
            ourLongestStringChain.add(currentString);
            currentString = stringChains.get(currentString).nextString;
        }
        return ourLongestStringChain.size() == 1 ? new ArrayList<>() : ourLongestStringChain;
    }

    private static void findLongestStringChain(String string, Map<String, StringChain> stringChains) {
        for (int i = 0; i < string.length(); i++) {
            String smallerString = getSmallerString(string, i);
            if (!stringChains.containsKey(smallerString)) continue;
            tryUpdateLongestStringChain(string, smallerString, stringChains);
        }
    }

    private static void tryUpdateLongestStringChain(String currentString, String smallerString, Map<String, StringChain> stringChains) {
        int smallerStringChainLength = stringChains.get(smallerString).maxChainLength;
        int currentStringChainLength = stringChains.get(currentString).maxChainLength;
        if (smallerStringChainLength + 1 > currentStringChainLength) {
            stringChains.get(currentString).maxChainLength = smallerStringChainLength + 1;
            stringChains.get(currentString).nextString = smallerString;
        }
    }

    private static String getSmallerString(String string, int index) {
        return string.substring(0, index) + string.substring(index + 1);
    }
}
