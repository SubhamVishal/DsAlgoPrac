package com.test.dsa.util.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//https://leetcode.com/problems/search-suggestions-system/
public class SearchSuggestionsSystem {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();
        Arrays.sort(products);
        //List<String> sortedProducts = Arrays.stream(products).sorted().collect(Collectors.toList());
        int left = 0;
        int right = products.length - 1;

        for (int i = 0; i < searchWord.length(); i++) {
            char c = searchWord.charAt(i);
            while (left <= right && (products[left].length() <= i || products[left].charAt(i) != c)) {
                left += 1;
            }
            while (left <= right && (products[right].length() <= i || products[right].charAt(i) != c)) {
                right -= 1;
            }
            result.add(new ArrayList<>());
            int remain = right - left + 1;
            for (int j = 0; j < Math.min(remain, 3); j++) {
                result.get(result.size() - 1).add(products[left + j]);
            }
        }
        return result;
    }
}
