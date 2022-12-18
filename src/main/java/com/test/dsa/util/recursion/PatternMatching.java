package com.test.dsa.util.recursion;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PatternMatching {
    static class Pair<T> {
        T first;
        T second;

        public Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?> pair = (Pair<?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }

    public boolean isMatch(String text, String pattern) {
        Map<Pair, Boolean> cache = new HashMap<>();
        return dfs(text, pattern, 0, 0, cache);
    }

    public boolean dfs(String text, String pattern, int i, int j, Map<Pair, Boolean> cache) {
        if (cache.containsKey(new Pair<>(i, j))) {
            return cache.get(new Pair<>(i, j));
        }
        if (i >= text.length() && j >= pattern.length()) {
            return true;
        }
        if (j >= pattern.length()) {
            return false;
        }
        boolean match = i < text.length() && (text.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '.');
        if ((j + 1) < pattern.length() && pattern.charAt(j + 1) == '*') {
            boolean resultOne = dfs(text, pattern, i, j + 2, cache);
            boolean resultTwo = match;
            if (match) {
                resultTwo = dfs(text, pattern, i + 1, j, cache);
            }
            cache.put(new Pair<>(i, j), resultOne || resultTwo);
            return cache.get(new Pair<>(i, j));
        }
        if (match) {
            cache.put(new Pair<>(i, j), dfs(text, pattern, i + 1, j + 1, cache));
            return cache.get(new Pair<>(i, j));
        }
        cache.put(new Pair<>(i, j), false);
        return false;
    }
}
