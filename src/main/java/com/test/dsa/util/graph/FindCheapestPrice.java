package com.test.dsa.util.graph;

import java.util.Arrays;
//bellman ford
//https://leetcode.com/problems/cheapest-flights-within-k-stops
public class FindCheapestPrice {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;
        for (int i = 0; i <= k; i++) {
            int[] tempPrices = prices.clone();
            for (int[] route : flights) {
                int source = route[0];
                int destination = route[1];
                int price = route[2];
                if (prices[source] == Integer.MAX_VALUE) {
                    continue;
                }
                if (prices[source] + price < tempPrices[destination]) {
                    tempPrices[destination] = prices[source] + price;
                }
            }
            prices = tempPrices;
        }
        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }
}
