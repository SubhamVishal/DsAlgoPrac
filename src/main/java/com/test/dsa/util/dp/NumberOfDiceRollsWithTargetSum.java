package com.test.dsa.util.dp;

import java.util.Arrays;

public class NumberOfDiceRollsWithTargetSum {
    final int MOD = 1000000007;
    public int numRollsToTarget(int dice, int faces, int target) {
        int[][] dp = new int[dice + 1][target + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }
        return helper(dice, faces, target, dp);
    }

    private int helper(int dice, int faces, int target, int[][] dp) {
        if(target < 0) {
            return 0;
        }
        if (dice == 0 && target != 0) {
            return 0;
        }
        if (target == 0 && dice != 0) {
            return 0;
        }
        if (target == 0) {
            return 1;
        }

        if (dp[dice][target] != -1) {
            return dp[dice][target];
        }
        int count = 0;
        for (int i = 1; i <= faces; i++) {
            count = (count + helper(dice - 1, faces, target - i, dp)) % MOD;
        }
        dp[dice][target] = count;
        return count;
    }

    public static void main(String[] args) {
        NumberOfDiceRollsWithTargetSum numberOfDiceRollsWithTargetSum = new NumberOfDiceRollsWithTargetSum();
        System.out.println(numberOfDiceRollsWithTargetSum.numRollsToTarget(1, 6, 3));
        System.out.println(numberOfDiceRollsWithTargetSum.numRollsToTarget(2, 6, 7));
        System.out.println(numberOfDiceRollsWithTargetSum.numRollsToTarget(30, 30, 500));
    }

}
