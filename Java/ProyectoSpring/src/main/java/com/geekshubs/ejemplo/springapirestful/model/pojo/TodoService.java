package com.geekshubs.ejemplo.springapirestful.model.pojo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private List<Todo> todos;

    private int todoCount = 3;
    public TodoService() {
        todos = new ArrayList<>();

        todos.add(new Todo(1, "Sergio", "Learn spring mvc", new Date(), false));
        todos.add(new Todo(2, "Pepe", "Learn java", new Date(), false));
        todos.add(new Todo(3, "Pepe", "Learn how to use date", new Date(), false));
    }

    public List<Todo> retrieveTodos(String username) {
        return todos.stream().filter(t -> t.getUser().compareTo(username) == 0).collect(Collectors.toList());
    }
}
