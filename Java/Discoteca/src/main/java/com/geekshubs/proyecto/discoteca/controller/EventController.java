package com.geekshubs.proyecto.discoteca.controller;

import com.geekshubs.proyecto.discoteca.model.dao.interfaces.IEventDAO;
import com.geekshubs.proyecto.discoteca.model.entities.Event;
import com.geekshubs.proyecto.discoteca.model.entities.User;
import com.geekshubs.proyecto.discoteca.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    public static final String ERROR_TITLE_SUPERADMIN = "You are not a superadmin";
    public static final String ERROR_MSG_SUPERADMIN = "You cannot create/edit/remove events if you're not logged in as superadmin";

    public static final String TITLE_EVENT_LIST = "Upcoming events";
    public static final String TITLE_EVENT_DETAILS = "Event details";
    public static final String TITLE_EVENT_CREATE = "Create an event";
    public static final String TITLE_EVENT_EDIT = "Edit an event";


    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy - HH:mm'h'");

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
        mav.addObject("title", TITLE_EVENT_LIST);
        mav.addObject("overviewLike", overviewLike);
        mav.addObject("eventList", eventList);
        mav.addObject("isFiltered", isFiltered);
        return mav;
    }

    @GetMapping("/details")
    public ModelAndView listEvents(@RequestParam Long eventId, Model model) {
        ModelAndView mav = new ModelAndView("events/details");
        mav.addObject("title", TITLE_EVENT_DETAILS);
        mav.addObject("event", eventDAO.findEventById(eventId));
        return mav;
    }


    // SOLO PARA SUPERADMIN
    @GetMapping("/create")
    public ModelAndView createEvent(WebRequest req) {
        ModelAndView mav;
        if(checkSuperAdminLogged(req)) {
            mav = new ModelAndView("events/form_new_edit");
            mav.addObject(new Event());
            mav.addObject("title", TITLE_EVENT_CREATE);
        }
        else
            mav = Util.errorPage(ERROR_TITLE_SUPERADMIN, ERROR_MSG_SUPERADMIN);

        return mav;
    }

    @GetMapping("/edit")
    public ModelAndView editEvent(@RequestParam Long eventId, WebRequest req) {
        ModelAndView mav;
        if(checkSuperAdminLogged(req)) {
            mav = new ModelAndView("events/form_new_edit");
            mav.addObject("event", eventDAO.findEventById(eventId));
            mav.addObject("title", TITLE_EVENT_EDIT);
        }
        else
            mav = Util.errorPage(ERROR_TITLE_SUPERADMIN, ERROR_MSG_SUPERADMIN);

        return mav;
    }

    @PostMapping("/store")
    public ModelAndView storeEvent(@Valid Event event, BindingResult result, WebRequest req) {
        ModelAndView mav;
        if(checkSuperAdminLogged(req)) {
            if (result.hasErrors()) {
                mav = new ModelAndView("events/form_new_edit");
                if(event.getId() != null)
                    mav.addObject("title", TITLE_EVENT_EDIT);
                else
                    mav.addObject("title", TITLE_EVENT_CREATE);
            } else {
                mav = new ModelAndView("redirect:list");
                if (event.getId() != null)
                    eventDAO.updateEvent(event);
                else
                    eventDAO.insertEvent(event);
            }
        }
        else
            mav = Util.errorPage(ERROR_TITLE_SUPERADMIN, ERROR_MSG_SUPERADMIN);

        return mav;
    }

    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView deleteEvent(Long eventId, WebRequest req) {
        if(checkSuperAdminLogged(req)) {
            eventDAO.deleteEventById(eventId);
            return new ModelAndView("redirect:list");
        }
        else
            return Util.errorPage(ERROR_TITLE_SUPERADMIN, ERROR_MSG_SUPERADMIN);
    }

    @ModelAttribute("dateFormat")
    public SimpleDateFormat dateFormatter() {
        return DATE_FORMAT;
    }

    public boolean checkSuperAdminLogged(WebRequest req) {
        User user = (User) req.getAttribute("loggedUser", WebRequest.SCOPE_SESSION);
        return user.getSuperadmin();
    }

    public boolean checkAdminLogged(WebRequest req) {
        User user = (User) req.getAttribute("loggedUser", WebRequest.SCOPE_SESSION);
        return user.getAdmin();
    }
}
