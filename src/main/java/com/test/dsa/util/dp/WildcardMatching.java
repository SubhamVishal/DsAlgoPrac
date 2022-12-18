package com.test.dsa.util.dp;

public class WildcardMatching {

    public boolean isMatch(String text, String pattern) {
        int textLen = text.length();
        int patternLen = pattern.length();
        boolean[][] dp = new boolean[patternLen + 1][textLen + 1];
        dp[patternLen][textLen] = true;
        for (int i = 0; i < textLen; i++) {
            dp[patternLen][i] = false;
        }

        for (int i = patternLen - 1; i >= 0; i--) {
            if (pattern.charAt(i) == '*') {
                dp[i][textLen] = dp[i + 1][textLen];
            } else {
                dp[i][textLen] = false;
            }
        }

        for (int i = patternLen - 1; i >= 0; i--) {
            for (int j = textLen - 1; j >= 0; j--) {
                if (pattern.charAt(i) == '*') {
                    dp[i][j] = (dp[i + 1][j] || dp[i][j + 1]);
                } else if ((pattern.charAt(i) == '?') || (pattern.charAt(i) == text.charAt(j))) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[0][0];
    }
}
