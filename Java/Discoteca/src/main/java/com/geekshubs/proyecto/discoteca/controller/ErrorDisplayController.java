package com.geekshubs.proyecto.discoteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error-display")
public class ErrorDisplayController {
    @GetMapping("")
    public ModelAndView showLoginErrorPage(@RequestParam String errorTitle, @RequestParam String errorMessage, Model model) {
        ModelAndView mav = new ModelAndView("error_display");
        mav.addObject("title", "Error");
        mav.addObject("errorTitle", errorTitle);
        mav.addObject("errorMessage", errorMessage);
        return mav;
    }
}
