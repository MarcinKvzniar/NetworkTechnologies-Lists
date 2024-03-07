package com.example.ntdemo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SimpleController {

    @GetMapping("/helloWorld")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/hello")
    public String helloSomeone(@RequestParam String name, @RequestParam String surname) {
        return "Hello " + name + " " + surname + "!";
    }

    @GetMapping("/add")
    public String add(@RequestParam int a, @RequestParam int b) {
        int sum = a + b;
        return a + " + " + b + " = " + sum;
    }

    @GetMapping("/multiply/{a}")
    public int multiply(@PathVariable int a) {
            return a * 10;
    }

}

