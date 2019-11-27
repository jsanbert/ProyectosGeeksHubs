package com.javierrodriguez.springsecurityexample.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicController {
    @GetMapping
    public String getMessage()
    {
        return "HOla mundo en abierto";
    }
}
