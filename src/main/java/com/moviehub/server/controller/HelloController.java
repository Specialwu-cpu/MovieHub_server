package com.moviehub.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "HelloController", description = "HelloController")
@RestController
@RequestMapping("/hello")
@CrossOrigin(origins = "*")
public class HelloController {
    @Operation(summary = "Hello world", description = "return Hello world to web",
            parameters = {}, tags = {"HelloController"})
    @ApiResponse(responseCode = "200", description = "Hello world")
    @GetMapping("/world")
    public String Hello() {
        return "Hello world";
    }
}
