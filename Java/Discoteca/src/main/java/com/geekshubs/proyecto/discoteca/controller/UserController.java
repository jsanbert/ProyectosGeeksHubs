package com.geekshubs.proyecto.discoteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/signup-event")
    public String signUpForAnEvent(@RequestParam Long eventId) {
        return "";
    }
}
