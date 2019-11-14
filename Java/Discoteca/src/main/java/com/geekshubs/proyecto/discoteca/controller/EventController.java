package com.geekshubs.proyecto.discoteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/events")
public class EventController {

    @GetMapping("/list")
    public String listEvents(@RequestParam(required = false) String filterBy, @RequestParam(required = false) String search) {
        return "";
    }

    @GetMapping("/details")
    public String listEvents(@RequestParam Long eventId) {
        return "";
    }
}
