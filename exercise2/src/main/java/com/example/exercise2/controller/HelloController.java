package com.example.exercise2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String sayHello (Model model) {
        model.addAttribute("message", "user, from the controller!");
        model.addAttribute("date", new java.util.Date());
        return "hello";
    }

    @RequestMapping("/hello/{name}")
    public String sayHelloToName (Model model, @PathVariable String name) {
        model.addAttribute("message", name + ", from the controller!");
        model.addAttribute("date", new java.util.Date());
        return "hello";
    }

}
