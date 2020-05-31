package com.ale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api")
public class RouterController {

    @RequestMapping("/a")
    public String distributeRouteByRedirect(){
        return "redirect:/api/roles";
    }

    @RequestMapping("/b")
    public String distributeRouteByForward(){
        return "forward:/api/roles";
    }

    @RequestMapping("/c")
    public String distributeRouteByRedirectWithParams(RedirectAttributes attributes){
        attributes.addAttribute("roleName","role_1");
        return "redirect:/api/roles";
    }






}
