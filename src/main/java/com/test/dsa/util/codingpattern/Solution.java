package com.test.dsa.util.codingpattern;

class Solution {

    public boolean repeatedSubstringPattern(String st) {
        int length = st.length();
        int[] Pi = new int[length];
        int p = 0;
        int i = 1;
        while (i < length) {
            if (st.charAt(p) == st.charAt(i)) {
                Pi[i] = ++p;
                i++;
            } else {
                if (p != 0) {
                    p = Pi[p - 1];
                } else {
                    Pi[i] = 0;
                    i++;
                }
            }
        }
        return Pi[length - 1] >= (length - Pi[length - 1]) && length % (length - Pi[length - 1]) == 0;
    }

    public static void main(String[] args) {
        Solution soln = new Solution();
        System.out.println(soln.repeatedSubstringPattern("abab"));
    }
}
