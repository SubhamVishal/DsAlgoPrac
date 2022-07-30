package com.test.dsa.util;

public class DummyMain {
    private static int solve(int m, int n) {
        if(m > n) return m-n;
        int count = 0;
        while(n > m) {
            if( n % 2 == 0) {
                count++;
                n/=2;
            }else {
                count+=2;
                n/=2;
                n += 1;
            }
        }
        count += m-n;
        return count;
    }

    public static void main(String[] args) {
        System.out.println(DummyMain.solve(10, 23));
    }
}
