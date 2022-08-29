package com.test.dsa.util.oops;

public class DUmmyListAbstractImpl extends DummyListAbstract{
    @Override
    public void print() {
        System.out.println("From DUmmyListAbstractImpl");
    }

    public void ownPrint() {
        System.out.println("ownPrint From DUmmyListAbstractImpl");
    }
}
