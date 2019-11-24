package com.geekshubs.proyecto.discoteca.api.controller;

import com.geekshubs.proyecto.discoteca.application.model.dao.interfaces.IEventDAO;
import com.geekshubs.proyecto.discoteca.application.model.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/{eventos.version.api}")
public class EventRestController {

    @Autowired
    IEventDAO eventDAO;

    @GetMapping("events")
    public List<Event> returnEvents() {
        return eventDAO.findAll();
    }

    @GetMapping("events/{id}")
    public Event returnEventById(@PathVariable Long eventId) {
        return eventDAO.findEventById(eventId);
    }

    @GetMapping("events/starting-from/{date}")
    public List<Event> returnEventsStartingFrom(@PathVariable @DateTimeFormat(pattern="dd-MM-yyyy") Date date) {
        return eventDAO.findEventsStartingFrom(date);
    }

    @GetMapping("events/add/{event}")
    public String addEvent(@PathVariable @Valid Event event) {
        eventDAO.insertEvent(event);
        return "inserted";
    }

}
