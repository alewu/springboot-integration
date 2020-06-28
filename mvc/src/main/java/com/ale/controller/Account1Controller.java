package com.ale.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class Account1Controller {
    @GetMapping("/test")
    public String test() {
        return "test";
    }
}