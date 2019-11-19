package com.geekshubs.ejemplo.springapirestful.model.pojo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private List<Todo> todos;

    private static Long todoCount = new Long(0);

    public TodoService() {
        todos = new ArrayList<>();

        todos.add(new Todo(++todoCount, "Sergio", "Learn spring mvc", new Date(), false));
        todos.add(new Todo(++todoCount, "Pepe", "Learn java", new Date(), false));
        todos.add(new Todo(++todoCount, "Pepe", "Learn how to use date", new Date(), false));
    }

    public List<Todo> retrieveTodos(String username) {
        return todos.stream().filter(t -> t.getUser().compareTo(username) == 0).collect(Collectors.toList());
    }

    public Todo retrieveTodoByUsernameAndId(String username, Long id) {
        return retrieveTodos(username).stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public boolean insertTodo(Todo todo) {
        todo.setId(++todoCount);
        return todos.add(todo);
    }

    public boolean updateTodo(String username, Todo todo) {
        Todo todoToUpdate = retrieveTodoByUsernameAndId(username, todo.getId());
        if(todoToUpdate == null)
            return false;

        todos.set(todos.indexOf(todoToUpdate), todo);
        return true;
    }
}
