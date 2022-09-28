package com.test.dsa.util.tree;

//https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/
class MctFromLeafValues {

    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        return dfsHelper(arr, dp, 0, arr.length - 1);
    }

    private int dfsHelper(int[] arr, int[][] dp, int start, int end) {
        if (start == end) {
            return 0;
        }

        if (dp[start][end] > 0) {
            return dp[start][end];
        }

        int ans = Integer.MAX_VALUE;
        for (int i = start; i < end; i++) {
            int maxLeft = getMaxOfRange(arr, start, i);
            int left = dfsHelper(arr, dp, start, i);

            int maxRight = getMaxOfRange(arr, i + 1, end);
            int right = dfsHelper(arr, dp, i + 1, end);

            ans = Math.min(ans, left + right + (maxLeft * maxRight));
        }
        dp[start][end] = ans;
        return ans;
    }

    private int getMaxOfRange(int[] arr, int start, int end) {
        int max = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            max = Math.max(arr[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        MctFromLeafValues soln = new MctFromLeafValues();
        soln.mctFromLeafValues(new int[]{6, 2, 4});
    }
}