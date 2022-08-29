package com.test.dsa.util.oops;

public class DummyListImpl extends DummyList{
    @Override
    public void print() {
        System.out.println("From DummyListImpl");
    }

    public void ownPrint() {
        System.out.println("ownPrint From DummyListImpl");
    }
}
