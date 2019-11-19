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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    public static final String ERROR_TITLE_SUPERADMIN = "You are not a superadmin";
    public static final String ERROR_MSG_SUPERADMIN = "You cannot create/edit/remove events if you're not logged in as superadmin";

    @Autowired
    private IEventDAO eventDAO;

    @GetMapping("/list")
    public ModelAndView listEvents(@RequestParam(required = false, defaultValue="") String overviewLike, Model model) {
        List<Event> eventList;
        boolean isFiltered = false;
        if(overviewLike.isEmpty())
            eventList = eventDAO.findAll();
        else {
            eventList = eventDAO.findEventsWithName(overviewLike);
            isFiltered = true;
        }
        
        model.addAttribute("title", "Upcoming events list");
        model.addAttribute("overviewLike", overviewLike);
        model.addAttribute("eventList", eventList);
        model.addAttribute("isFiltered", isFiltered);
        return new ModelAndView("events/list");
    }

    @GetMapping("/details")
    public ModelAndView listEvents(@RequestParam Long eventId, Model model) {
        model.addAttribute("title", "Event details");
        model.addAttribute("event", eventDAO.findEventById(eventId));
        return new ModelAndView("events/details");
    }


    // SOLO PARA SUPERADMIN
    @GetMapping("/create")
    public ModelAndView createEvent(Model model, HttpSession session) {
        if((boolean) session.getAttribute("superadminLogged")) {
            model.addAttribute(new Event());
            model.addAttribute("title", "Create an event");
            return new ModelAndView("events/form_new_edit");
        }
        else
            return new ModelAndView("redirect:/error?errorTitle="+ERROR_TITLE_SUPERADMIN+"&errorMessage="+ERROR_MSG_SUPERADMIN);
    }

    @GetMapping("/edit")
    public ModelAndView editEvent(@RequestParam Long eventId, Model model, HttpSession session) {
        if((boolean) session.getAttribute("superadminLogged")) {
            model.addAttribute("event", eventDAO.findEventById(eventId));
            model.addAttribute("title", "Edit an event");
            return new ModelAndView("events/form_new_edit");
        }
        else
            return new ModelAndView("redirect:/error?errorTitle="+ERROR_TITLE_SUPERADMIN+"&errorMessage="+ERROR_MSG_SUPERADMIN);
    }

    @PostMapping("/store")
    public ModelAndView storeEvent(@Valid Event event, BindingResult result, Model model, HttpSession session) {
        if((boolean) session.getAttribute("superadminLogged")) {
            if (result.hasErrors()) {
                model.addAttribute("title", "Create an event");
                return new ModelAndView("events/form_new_edit");
            }
            if (event.getId() != null)
                eventDAO.updateEvent(event);
            else
                eventDAO.insertEvent(event);

            return new ModelAndView("redirect:create-event");
        }
        else
            return new ModelAndView("redirect:/error?errorTitle="+ERROR_TITLE_SUPERADMIN+"&errorMessage="+ERROR_MSG_SUPERADMIN);
    }

    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam Long eventId, Model model, HttpSession session) {
        if((boolean) session.getAttribute("superadminLogged")) {
            eventDAO.deleteEventById(eventId);
            return new ModelAndView("redirect:/events/list");
        }
        else
            return new ModelAndView("redirect:/error?errorTitle="+ERROR_TITLE_SUPERADMIN+"&errorMessage="+ERROR_MSG_SUPERADMIN);
    }
}
