package com.test.dsa.util.graph;


/*
After catching your classroom students cheating before, you realize your students are getting craftier and hiding words in 2D grids of letters. The word may start anywhere in the grid, and consecutive letters can be either immediately below or immediately to the right of the previous letter.

Given a grid and a word, write a function that returns the location of the word in the grid as a list of coordinates. If there are multiple matches, return any one.

grid1 = [
    ['c', 'c', 't', 'n', 'a', 'x'],
    ['c', 'c', 'a', 't', 'n', 't'],
    ['a', 'c', 'n', 'n', 't', 't'],
    ['t', 'n', 'i', 'i', 'p', 'p'],
    ['a', 'o', 'o', 'o', 'a', 'a'],
    ['s', 'a', 'a', 'a', 'o', 'o'],
    ['k', 'a', 'i', 'o', 'k', 'i'],
]
word1 = "catnip"


word2 = "cccc"
word3 = "s"
word4 = "ant"
word5 = "aoi"
word6 = "ki"
word7 = "aaoo"
word8 = "ooo"

grid2 = [['a']]
word9 = "a"

find_word_location(grid1, word1) => [ (1, 1), (1, 2), (1, 3), (2, 3), (3, 3), (3, 4) ]
find_word_location(grid1, word2) =>
       [(0, 0), (1, 0), (1, 1), (2, 1)]
    OR [(0, 0), (0, 1), (1, 1), (2, 1)]
find_word_location(grid1, word3) => [(5, 0)]
find_word_location(grid1, word4) => [(0, 4), (1, 4), (2, 4)] OR [(0, 4), (1, 4), (1, 5)]
find_word_location(grid1, word5) => [(4, 5), (5, 5), (6, 5)]
find_word_location(grid1, word6) => [(6, 4), (6, 5)]
find_word_location(grid1, word7) => [(5, 2), (5, 3), (5, 4), (5, 5)]
find_word_location(grid1, word8) => [(4, 1), (4, 2), (4, 3)]

find_word_location(grid2, word9) => [(0, 0)]

Complexity analysis variables:

r = number of rows
c = number of columns
w = length of the word
*/

import java.io.*;
import java.util.*;

public class FindWords {

    public String getWord(String[] words, String target) {
        for (String word : words) {
            if (isFound(getCountMap(word), target)) {
                return word;
            }
        }
        return "_";
    }

    private boolean isFound(Map<Character, Integer> charCountMap, String target) {
        int numUniqueCharLeft = charCountMap.size();
        for (int i = 0; i < target.length(); i++) {
            char curr = target.charAt(i);
            if (!charCountMap.containsKey(curr)) {
                continue;
            } else {
                int count = charCountMap.get(curr);
                if (count == 1) {
                    numUniqueCharLeft -= 1;
                    if (numUniqueCharLeft == 0) {
                        return true;
                    }
                    charCountMap.remove(curr);
                } else {
                    charCountMap.put(curr, count - 1);
                }
            }
        }
        return false;
    }

    private Map<Character, Integer> getCountMap(String str) {
        Map<Character, Integer> charCount = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            charCount.put(str.charAt(i), charCount.getOrDefault(str.charAt(i), 0) + 1);
        }
        return charCount;
    }


    public List<List<Integer>> getOcuurances(char[][] grid, String string) {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                List<List<Integer>> resList = new ArrayList<>();
                if (dfs(grid, resList, string, 0, i, j)) {
                    return resList;
                }
            }
        }
        return null;
    }

    private boolean dfs(char[][] grid, List<List<Integer>> coordList, String string, int charIndex, int x, int y) {
        if (charIndex == string.length()) {
            return true;
        }
        if (!isValidMove(x, y, grid)) {
            return false;
        }
        char curr = string.charAt(charIndex);
        if (grid[x][y] == curr) {
            coordList.add(Arrays.asList(x, y));
            boolean downRes = dfs(grid, coordList, string, charIndex + 1, x + 1, y);
            if (downRes) {
                return true;
            }
            boolean leftRes = dfs(grid, coordList, string, charIndex + 1, x, y + 1);
            if (leftRes) {
                return true;
            }
            coordList.remove(coordList.size() - 1);
        }
        return false;
    }


    private boolean isValidMove(int x, int y, char[][] grid) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    public static void main(String[] argv) {
        char[][] grid1 = {
                {'c', 'c', 't', 'n', 'a', 'x'},
                {'c', 'c', 'a', 't', 'n', 't'},
                {'a', 'c', 'n', 'n', 't', 't'},
                {'t', 'n', 'i', 'i', 'p', 'p'},
                {'a', 'o', 'o', 'o', 'a', 'a'},
                {'s', 'a', 'a', 'a', 'o', 'o'},
                {'k', 'a', 'i', 'o', 'k', 'i'}
        };
        String word1 = "catnip";
        String word2 = "cccc";
        String word3 = "s";
        String word4 = "ant";
        String word5 = "aoi";
        String word6 = "ki";
        String word7 = "aaoo";
        String word8 = "ooo";

        char[][] grid2 = {{'a'}};
        String word9 = "a";

        FindWords soln = new FindWords();
        System.out.println(soln.getOcuurances(grid1, word1));
        System.out.println(soln.getOcuurances(grid1, word2));
        System.out.println(soln.getOcuurances(grid1, word3));
        System.out.println(soln.getOcuurances(grid1, word4));
        System.out.println(soln.getOcuurances(grid1, word5));//
        System.out.println(soln.getOcuurances(grid1, word6));//
        System.out.println(soln.getOcuurances(grid1, word7));//
        System.out.println(soln.getOcuurances(grid1, word8));
        System.out.println(soln.getOcuurances(grid2, word9));//


        // System.out.println(soln.getWord(words, string1));
        // System.out.println(soln.getWord(words, string2));
        // System.out.println(soln.getWord(words, string3));
        // System.out.println(soln.getWord(words, string4));
        // System.out.println(soln.getWord(words, string5));
        // System.out.println(soln.getWord(words, string6));
    }
}


