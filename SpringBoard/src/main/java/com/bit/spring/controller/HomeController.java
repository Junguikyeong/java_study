package com.bit.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String showIndex() {
//        System.out.println(model.getAttribute("message"));
        return "index";
    }

}