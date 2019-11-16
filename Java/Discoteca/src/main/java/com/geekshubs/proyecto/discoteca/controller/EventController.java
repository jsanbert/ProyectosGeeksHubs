package com.geekshubs.proyecto.discoteca.controller;

import com.geekshubs.proyecto.discoteca.model.dao.interfaces.IEventDAO;
import com.geekshubs.proyecto.discoteca.model.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private IEventDAO eventDAO;

    @GetMapping("/list")
    public String listEvents(@RequestParam(required = false, defaultValue="") String overviewLike, Model model) {
        List<Event> eventList;
        boolean isFiltered = false;
        if(overviewLike.isEmpty())
            eventList = eventDAO.findAll();
        else {
            eventList = eventDAO.findEventsWithName(overviewLike);
            isFiltered = true;
        }

        model.addAttribute("overviewLike", overviewLike);
        model.addAttribute("eventList", eventList);
        model.addAttribute("isFiltered", isFiltered);
        return "events/list";
    }

    @GetMapping("/details")
    public String listEvents(@RequestParam Long eventId, Model model) {
        model.addAttribute("title", "Event details");
        model.addAttribute("event", eventDAO.findEventById(eventId));
        return "events/details";
    }
}
