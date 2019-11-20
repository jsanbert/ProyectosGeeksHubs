package com.geekshubs.proyecto.discoteca.controller;

import com.geekshubs.proyecto.discoteca.model.dao.interfaces.IEventDAO;
import com.geekshubs.proyecto.discoteca.model.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    public static final String ERROR_TITLE_SUPERADMIN = "You are not a superadmin";
    public static final String ERROR_MSG_SUPERADMIN = "You cannot create/edit/remove events if you're not logged in as superadmin";

    public static final SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy - HH:mm");

    private static HttpSession session;

    @Autowired
    private IEventDAO eventDAO;

    @GetMapping("/list")
    public ModelAndView listEvents(@RequestParam(required = false, defaultValue="") String overviewLike) {
        List<Event> eventList;
        boolean isFiltered = false;
        if(overviewLike.isEmpty())
            eventList = eventDAO.findAll();
        else {
            eventList = eventDAO.findEventsWithName(overviewLike);
            isFiltered = true;
        }

        ModelAndView mav = new ModelAndView("events/list");
        mav.addObject("title", "Upcoming events list");
        mav.addObject("overviewLike", overviewLike);
        mav.addObject("eventList", eventList);
        mav.addObject("isFiltered", isFiltered);
        return mav;
    }

    @GetMapping("/details")
    public ModelAndView listEvents(@RequestParam Long eventId, Model model) {
        ModelAndView mav = new ModelAndView("events/details");
        mav.addObject("title", "Event details");
        mav.addObject("event", eventDAO.findEventById(eventId));
        return mav;
    }


    // SOLO PARA SUPERADMIN
    @GetMapping("/create")
    public ModelAndView createEvent(HttpServletRequest req) {
        ModelAndView mav;
        if(checkSuperAdminLogged(req.getSession())) {
            mav = new ModelAndView("events/form_new_edit");
            mav.addObject(new Event());
            mav.addObject("title", "Create an event");
        }
        else
            mav = errorPage(ERROR_TITLE_SUPERADMIN, ERROR_MSG_SUPERADMIN);

        return mav;
    }

    @GetMapping("/edit")
    public ModelAndView editEvent(@RequestParam Long eventId, Model model, HttpServletRequest req) {
        ModelAndView mav;
        if(checkSuperAdminLogged(req.getSession())) {
            mav = new ModelAndView("events/form_new_edit");
            mav.addObject("event", eventDAO.findEventById(eventId));
            mav.addObject("title", model.getAttribute("title"));
        }
        else
            mav = errorPage(ERROR_TITLE_SUPERADMIN, ERROR_MSG_SUPERADMIN);

        return mav;
    }

    @PostMapping("/store")
    public ModelAndView storeEvent(@Valid Event event, BindingResult result, HttpServletRequest req) {
        ModelAndView mav;
        if(checkSuperAdminLogged(req.getSession())) {
            if (result.hasErrors()) {
                mav = new ModelAndView("events/form_new_edit");
            } else {
                mav = new ModelAndView("redirect:create");
                mav.addObject("title", "Create an event");
                if (event.getId() != null)
                    eventDAO.updateEvent(event);
                else
                    eventDAO.insertEvent(event);
            }
        }
        else
            mav = errorPage(ERROR_TITLE_SUPERADMIN, ERROR_MSG_SUPERADMIN);

        return mav;
    }

    @PostMapping("/delete")
    public ModelAndView deleteEvent(@RequestParam Long eventId, Model model, HttpServletRequest req) {
        if(checkSuperAdminLogged(req.getSession())) {
            eventDAO.deleteEventById(eventId);
            return new ModelAndView("redirect:events/list");
        }
        else
            return errorPage(ERROR_TITLE_SUPERADMIN, ERROR_MSG_SUPERADMIN);
    }

    @ModelAttribute("dateFormat")
    public SimpleDateFormat dateFormatter() {
        return df;
    }

    public boolean checkSuperAdminLogged(HttpSession session) {
        Object superAdminIsLogged = session.getAttribute("superadminLogged");
        if(superAdminIsLogged == null)
            return false;
        else
            return (boolean) superAdminIsLogged;
    }

    public ModelAndView errorPage(String errorTitle, String errorMessage) {
        return new ModelAndView("redirect:/error-display?errorTitle="+errorTitle+"&errorMessage="+errorMessage);
    }
}
