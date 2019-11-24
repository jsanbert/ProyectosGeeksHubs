package com.geekshubs.proyecto.discoteca.application.controller;

import com.geekshubs.proyecto.discoteca.application.Util;
import com.geekshubs.proyecto.discoteca.application.model.dao.interfaces.IEventDAO;
import com.geekshubs.proyecto.discoteca.application.model.dao.interfaces.IRegisterDAO;
import com.geekshubs.proyecto.discoteca.application.model.dao.interfaces.IUserDAO;
import com.geekshubs.proyecto.discoteca.application.model.entities.Register;
import com.geekshubs.proyecto.discoteca.application.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("loggedUser")
@RequestMapping("/users")
public class UserController {

    public static final String TITLE_USER_LOGIN_FORM = "User login form";
    public static final String TITLE_USER_REGISTRATION_FORM = "User registration form";
    public static final String TITLE_EVENT_SIGNDOWN_FORM = "Sign down from an event";
    public static final String TITLE_SHOWTOKEN_FORM = "This is your token, save it in order to sign down!";
    public static final String TEXT_ERROR_TOKEN = "Error: token input does not exist";
    public static final String TEXT_SUCCESS_EVENT_SIGNDOWN = "Success: you were signed down from the event";
    public static final String TITLE_ERROR_USER_REGISTERED_EVENT = "You already registered for this event";
    public static final String MESSAGE_ERROR_USER_REGISTERED_EVENT = "You cannot register again";


    public static final String TITLE_ERROR_FULL_CAPACITY = "Your entry could not be registered";
    public static final String MESSAGE_ERROR_FULL_CAPACITY = "We are sorry to inform you that the seating for this event is full";

    public static final String MESSAGE_ERROR_USER_EXISTS = "An account already exists for this username";
    public static final String MESSAGE_ERROR_USER_PASSWORD_INCORRECT = "User or password incorrect";

    @Autowired
    private IEventDAO eventDAO;

    @Autowired
    private IRegisterDAO registerDAO;

    @Autowired
    private IUserDAO userDAO;

    @GetMapping("/signup-event")
    public ModelAndView signUpForAnEvent(@RequestParam Long eventId, WebRequest req) {
        ModelAndView mav;
        Register register = new Register();
        User user = (User) req.getAttribute("loggedUser", WebRequest.SCOPE_SESSION);
        Long capacity = eventDAO.getCapacityByEventId(eventId);
        if(capacity > 0) {
            if(registerDAO.checkUserRegisteredToEvent(eventId, user.getUsername()))
                mav = Util.errorPage(TITLE_ERROR_USER_REGISTERED_EVENT, MESSAGE_ERROR_USER_REGISTERED_EVENT);
            else {
                register.setUserId(user.getId());
                register.setEventId(eventId);
                register.setToken(Util.generateToken());
                registerDAO.insertRegister(register);
                mav = new ModelAndView("registers/show_token");
                mav.addObject("token", register.getToken());
                mav.addObject("title", TITLE_SHOWTOKEN_FORM);
            }
        }
        else
            mav = Util.errorPage(TITLE_ERROR_FULL_CAPACITY, MESSAGE_ERROR_FULL_CAPACITY);

        return mav;
    }

    @GetMapping("/signdown-event")
    public ModelAndView signDownFromAnEvent() {
        ModelAndView mav = new ModelAndView("registers/form_signdown");
        mav.addObject("title", TITLE_EVENT_SIGNDOWN_FORM);
        return mav;
    }

    @PostMapping("/perform-signdown")
    public ModelAndView performSignDown(@RequestParam String token) {
        ModelAndView mav = new ModelAndView("registers/form_signdown");
        Register register = registerDAO.findRegisterByToken(token);
        mav.addObject("error", false);
        mav.addObject("title", TITLE_EVENT_SIGNDOWN_FORM);
        if(register == null) {
            mav.addObject("error", true);
            mav.addObject("errorText", TEXT_ERROR_TOKEN);
        } else {
            registerDAO.deleteRegisterById(register.getId());
            mav.addObject("successText", TEXT_SUCCESS_EVENT_SIGNDOWN);
        }
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView loginForm() {
        ModelAndView mav = new ModelAndView("users/login");
        mav.addObject("title", TITLE_USER_LOGIN_FORM);
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/perform-login")
    public ModelAndView performLogin(@Validated(User.LoginValidation.class) User user, BindingResult result, WebRequest req) {
        ModelAndView mav;
        String username = user.getUsername();
        String password = user.getPassword();

        User userLogged;

        if(result.hasErrors() || (userLogged = userDAO.getLoggedUser(username, password)) == null) {
            mav = new ModelAndView("users/login");
            mav.addObject("title", TITLE_USER_LOGIN_FORM);
            mav.addObject("userOrPasswordIncorrect", MESSAGE_ERROR_USER_PASSWORD_INCORRECT);
        } else {
            login(req, userLogged);
            mav = new ModelAndView("redirect:/events/list");
        }

        return mav;
    }

    @GetMapping("/logout")
    public ModelAndView performLogout(WebRequest req, SessionStatus status) {
        logout(req, status);
        ModelAndView mav = new ModelAndView("redirect:/events/list");
        return mav;
    }

    @GetMapping("/register")
    public ModelAndView userRegistrationForm() {
        User user = new User();
        user.setAdmin(false);
        user.setSuperadmin(false);

        ModelAndView mav = new ModelAndView("users/register");
        mav.addObject("title", TITLE_USER_REGISTRATION_FORM);
        mav.addObject(user);
        return mav;
    }

    @PostMapping("/store-user-registration")
    public ModelAndView storeUserRegistration(@Validated(User.RegisterValidation.class) User user, BindingResult result, WebRequest req) {
        ModelAndView mav = new ModelAndView("users/register");
        mav.addObject("title", TITLE_USER_REGISTRATION_FORM);
        if(!user.getUsername().isEmpty() && userDAO.userExists(user.getUsername())) {
            result.rejectValue("username", "error.username", MESSAGE_ERROR_USER_EXISTS);
        }
        if(!result.hasErrors()) {
            userDAO.insertUser(user);
            login(req, user);
            mav = new ModelAndView("redirect:/events/list");
        }
        return mav;
    }

    public static void login(WebRequest req, User user) {
        req.setAttribute("loggedUser", user, WebRequest.SCOPE_SESSION);
        System.out.println("isAdmin: " + user.getAdmin());
        System.out.println("isSuperadmin: " + user.getSuperadmin());
    }

    public static void logout(WebRequest req, SessionStatus status) {
        status.setComplete();
        req.removeAttribute("loggedUser", WebRequest.SCOPE_SESSION);
    }

    @ModelAttribute("loggedUser")
    public User loggedUser() {
        return null;
    }
}
