package com.example.exercise.exercise1;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MyController {
    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String hello(@PathVariable(value = "name") String name) {
        return "Hello, "+ name;
    }

    @RequestMapping(value = "/add")
    public String add(@RequestParam(value = "number1") int a, @RequestParam(value = "number2") int b, Model model) {

        int num3 = a + b;
        model.addAttribute("num3", num3);
        return "Result of " + a + " + " + b + " = " + num3;
    }
    
}
