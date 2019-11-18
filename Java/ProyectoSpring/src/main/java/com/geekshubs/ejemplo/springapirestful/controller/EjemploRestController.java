package com.geekshubs.ejemplo.springapirestful.controller;

import com.geekshubs.ejemplo.springapirestful.model.pojo.Todo;
import com.geekshubs.ejemplo.springapirestful.model.pojo.TodoService;
import com.geekshubs.ejemplo.springapirestful.model.pojo.WelcomeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EjemploRestController {

    @Autowired
    private TodoService todoObject;

    @GetMapping("/api/v1/welcome")
    public String welcome() {
        return "Hello world";
    }

    @GetMapping("/api/v1/welcome-bean")
    public WelcomeBean welcomeBean() {
        return new WelcomeBean();
    }

    @GetMapping("/api/v1/welcome-bean/msg/{msg}")
    public WelcomeBean welcomeBean(@PathVariable String msg) {
        return new WelcomeBean(msg);
    }

    @GetMapping("/api/v1/todo")
    public TodoService todo() {
        return todoObject;
    }

    @GetMapping("/api/v1/users/{username}/todos")
    public List<Todo> todosFromUser(@PathVariable String username) {
        return todoObject.retrieveTodos(username);
    }
}
