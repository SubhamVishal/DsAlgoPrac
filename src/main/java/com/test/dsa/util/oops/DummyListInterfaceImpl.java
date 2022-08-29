package com.test.dsa.util.oops;

public class DummyListInterfaceImpl implements DummyListIntegface {

    @Override
    public void print() {
        System.out.println("From DummyListInterfaceImpl");
    }

    public void ownPrint() {
        System.out.println("ownPrint From DummyListInterfaceImpl");
    }

}
