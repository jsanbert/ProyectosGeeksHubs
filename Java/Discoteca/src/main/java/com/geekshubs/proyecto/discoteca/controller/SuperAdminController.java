package com.geekshubs.proyecto.discoteca.controller;

import com.geekshubs.proyecto.discoteca.model.dao.interfaces.IEventDAO;
import com.geekshubs.proyecto.discoteca.model.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/superadmin")
public class SuperAdminController {

    @Autowired
    private IEventDAO eventDAO;

    @GetMapping("/create-event")
    public String createEvent(@Valid Event event) {
        eventDAO.insertEvent(event);
        return "";
    }
}
