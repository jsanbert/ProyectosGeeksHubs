package com.geekshubs.proyecto.discoteca.controller;

import com.geekshubs.proyecto.discoteca.model.dao.interfaces.IEventDAO;
import com.geekshubs.proyecto.discoteca.model.dao.interfaces.IRegisterDAO;
import com.geekshubs.proyecto.discoteca.model.entities.Event;
import com.geekshubs.proyecto.discoteca.model.entities.Register;
import com.geekshubs.proyecto.discoteca.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IEventDAO eventDAO;

    @Autowired
    private IRegisterDAO registerDAO;

    @GetMapping("/signup-event")
    public String signUpForAnEvent(@RequestParam Long eventId, Model model) {
        Event event = eventDAO.findEventById(eventId);
        Register register = new Register();
        register.setEventId(event.getId());
        model.addAttribute("title", "Sign up form");
        model.addAttribute("event", event);
        model.addAttribute("register", register);
        return "registers/form_signup";
    }

    @PostMapping("/store-register")
    public String storeRegister(@Valid Register register, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("title", "Sign up form");
            return "registers/form_signup";
        }
        register.setToken(Token.generateToken());
        registerDAO.insertRegister(register);
        model.addAttribute("token", register.getToken());
        model.addAttribute("title", "This is your token, save it in order to sign down!");
        return "registers/show_token";
    }

    @GetMapping("/signdown-event")
    public String signDownFromAnEvent(Model model) {
        model.addAttribute("title", "Sign down from an event");
        return "registers/form_signdown";
    }

    @PostMapping("/perform-signdown")
    public String performSignDown(@RequestParam String token, Model model) {
        Register register = registerDAO.findRegisterByToken(token);
        model.addAttribute("error", false);
        model.addAttribute("title", "Sign down from an event");
        if(register == null) {
            model.addAttribute("error", true);
            model.addAttribute("errorText", "Error: token input does not exist");
        }
        else {
            registerDAO.deleteRegisterById(register.getId());
            model.addAttribute("successText", "Success: you were signed down from the event");
        }
        return "registers/form_signdown";
    }
}
