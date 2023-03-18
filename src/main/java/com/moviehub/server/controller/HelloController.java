package com.moviehub.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {
    @RequestMapping(value = "hello")
    public String Hello() {
        return "Hello world";
    }
}
