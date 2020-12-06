package com.ale.other;

import org.springframework.stereotype.Component;

@Component
public class A {
    private final B b;
    public A(final B b) {
        this.b = b;
    }
    public void prt() {
        System.out.println("in a prt");
    }


}
