package com.geekshubs.ejemplo.springapirestful.controller;

import com.geekshubs.ejemplo.springapirestful.model.pojo.Todo;
import com.geekshubs.ejemplo.springapirestful.model.pojo.TodoService;
import com.geekshubs.ejemplo.springapirestful.model.pojo.WelcomeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class EjemploRestController {

    @Autowired
    private TodoService todoService;

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
        return todoService;
    }

    @GetMapping("/api/v1/users/{username}/todos")
    public List<Todo> todosFromUser(@PathVariable String username) {
        return todoService.retrieveTodos(username);
    }

    @GetMapping("/api/v1/users/{username}/todos/{id}")
    public Todo todoById(@PathVariable String username, @PathVariable Long id) {
        return todoService.retrieveTodoByUsernameAndId(username, id);
    }

    @PostMapping("/api/v1/users/{username}/todos")
    public ResponseEntity<Boolean> insertTodo(@PathVariable String username, @RequestBody Todo todo) {

        todo.setUser(username);
        todo.setTargetDate(new Date());
        boolean inserted = todoService.insertTodo(todo);
        return new ResponseEntity<>(inserted, HttpStatus.OK);
    }

    @PutMapping("/api/v1/users/{username}/todos")
    public ResponseEntity<Boolean> updateTodo(@PathVariable String username, @RequestBody Todo todo) {
        boolean updated = todoService.updateTodo(username, todo);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
