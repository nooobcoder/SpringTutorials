package com.recipefinder.recipefinder.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "index"})
    public String getIndexPage() {
        System.out.println("Some logging for you, enjoy!");
        return "index";
    }
}
