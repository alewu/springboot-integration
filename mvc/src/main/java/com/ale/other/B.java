package com.ale.other;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class B {
    private final A a;
    public B(@Lazy final A a) {
        this.a = a;
    }
    public void prt() {
        a.prt();
    }


}
