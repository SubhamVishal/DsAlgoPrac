package com.test.dsa.util.oops;

public class Main {
    public static void main(String[] args) {
        DummyListAbstract dummyList = new DUmmyListAbstractImpl();
        DUmmyListAbstractImpl dummyList1 = new DUmmyListAbstractImpl();
        dummyList.print();
        dummyList1.print();
        dummyList1.ownPrint();
    }
}
