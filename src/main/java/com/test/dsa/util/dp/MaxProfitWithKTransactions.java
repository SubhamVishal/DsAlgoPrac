package com.test.dsa.util.dp;

/*
    K represents the number of transactions you are allowed to make. One transaction
    consists of buyinh the stock on a given day and selling it on another, later day.
    prices = [5, 11, 3, 50, 60, 90]
    k = 2

    Output: 93 : Buy: 5, Sell: 11, Buy: 3, Sell: 90
 */

// O(nk) time | O(n) space - where n is the number of prices and k is the number of transactions
public class MaxProfitWithKTransactions {
    public static int maxProfitWithKTransactions(int[] prices, int k) {
        if(prices.length == 0) {
            return 0;
        }
        int[][] profits = new int[k + 1][prices.length];
        for(int t = 1; t < k + 1; t++) {
            int maxThusFar = Integer.MIN_VALUE;
            for(int d = 1; d < prices.length; d++) {
                //at each day two choices: either sell, or don't do anything: if sell the find a previous day wher(Profit[t -1][x] -price[x] is max) and add that to current price or take profit of previous day with same
                //number of transaction. ie. profits[t][d -1]
                maxThusFar = Math.max(maxThusFar, profits[t - 1][d - 1] - prices[d - 1]);
                profits[t][d] = Math.max(profits[t][d - 1], maxThusFar + prices[d]);
            }
        }
        return profits[k][prices.length - 1];
    }
}
