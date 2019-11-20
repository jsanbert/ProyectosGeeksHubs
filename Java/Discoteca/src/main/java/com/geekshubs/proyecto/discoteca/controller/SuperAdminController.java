package com.geekshubs.proyecto.discoteca.controller;

import com.geekshubs.proyecto.discoteca.model.dao.interfaces.IEventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/superadmin")
public class SuperAdminController {

    @Autowired
    private IEventDAO eventDAO;

    private static HttpSession session;

    @GetMapping("/login")
    public String login(HttpServletRequest req) {
        session = req.getSession();
        session.setAttribute("superadminLogged", true);
        return "superadmin/menu";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req) {
        session = req.getSession();
        session.setAttribute("superadminLogged", false);
        return "superadmin/menu";
    }
}
