package com.test.dsa.util.math;

public class NextSmallestPalindrome {
    private boolean isPalindrome(String a) {
        return a.equals(new StringBuilder(a).reverse().toString());
    }

    public static String addOneV2(String a) {
        String b = "1";
        int i = a.length();
        int j = b.length();
        int k = Math.max(i, j) + 1; // room for carryover
        char[] c = new char[k];
        for (int digit = 0; k > 0; digit /= 10) {
            if (i > 0)
                digit += a.charAt(--i) - '0';
            if (j > 0)
                digit += b.charAt(--j) - '0';
            c[--k] = (char) ('0' + digit % 10);
        }
        for (k = 0; k < c.length - 1 && c[k] == '0'; k++) {/*Skip leading zeroes*/}
        return new String(c, k, c.length - k);
    }

    private String addOne(String a) {
        return String.valueOf(Long.parseLong(a)  + 1);
    }

    public String solve(String a) {
        if (isPalindrome(a)) {
            a = addOneV2(a);
        }
        if (a.length() % 2 == 1) {
            return handleOdd(a);
        } else {
            return handleEven(a);
        }
    }

    private int compare(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            if ((long) a.charAt(i) > (long) b.charAt(i)) {
                return 1;
            } else if ((long) a.charAt(i) < (long) b.charAt(i)) {
                return -1;
            }
        }
        return 0;
    }

    private String handleEven(String a) {
        int n = a.length();
        String left = a.substring(0, n / 2);
        String right = a.substring(n / 2);
        if (compare(new StringBuilder(left).reverse().toString(), right) != 1) {
            left = addOneV2(left);
        }
        return left + new StringBuilder(left).reverse();
    }

    private String handleOdd(String a) {
        int n = a.length();
        String left = a.substring(0, n / 2);
        char mid = a.charAt(n / 2);
        String right = a.substring(n / 2 + 1);
        if (compare(new StringBuilder(left).reverse().toString(), right) == 1) {
            return left + mid + new StringBuilder(left).reverse();
        } else {
            left = left + mid;
            left = addOneV2(left);
            return left + new StringBuilder(left).reverse().substring(1);
        }
    }

    public static void main(String[] args) {
        NextSmallestPalindrome nextSmallestPalindrome = new NextSmallestPalindrome();
        System.out.println(nextSmallestPalindrome.solve("88245"));
        //61433416
        System.out.println(nextSmallestPalindrome.solve("61423221"));
        //10000000001
        System.out.println(nextSmallestPalindrome.solve("9999999999"));

        //184963788291360063192887369481
        System.out.println(nextSmallestPalindrome.solve("184963788291359953192887369481"));

    }
}
