package com.geekshubs.proyecto.discoteca.controller;

import com.geekshubs.proyecto.discoteca.model.dao.interfaces.IEventDAO;
import com.geekshubs.proyecto.discoteca.model.entities.Event;
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
@RequestMapping("/superadmin")
public class SuperAdminController {

    @Autowired
    private IEventDAO eventDAO;

    @GetMapping("/create-event")
    public String createEvent(Model model) {
        model.addAttribute(new Event());
        model.addAttribute("title", "Create an event");
        return "events/form_new_edit";
    }

    @GetMapping("/edit-event")
    public String editEvent(@RequestParam Long eventId, Model model) {
        model.addAttribute("event", eventDAO.findEventById(eventId));
        return "events/form_new_edit";
    }

    @PostMapping("/store-event")
    public String storeEvent(@Valid Event event, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("titulo", "Create an event");
            return "events/form_new_edit";
        }
        if(event.getId() != null)
            eventDAO.updateEvent(event);
        else
            eventDAO.insertEvent(event);
        return "redirect:create-event";
    }

    // CAMBIAR POR POST
    @GetMapping("/delete-event")
    public String delete(@RequestParam Long eventId) {
        eventDAO.deleteEventById(eventId);
        return "redirect:/events/list";
    }
}
