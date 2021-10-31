package com.ale.other;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping()
public class HelloController {

    @GetMapping("/sayHello/{a}")
    public String hello(@PathVariable("a") String a) {
        return "hello " + a;
    }

}
