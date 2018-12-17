package com.todo.demo.controller;


import com.todo.demo.aspects.Restrict;
import com.todo.demo.dto.AllTodos;
import com.todo.demo.model.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/todo")
public class TodoController {
    
    private static Logger log=LoggerFactory.getLogger(TodoController.class);
    private final TodoRepository repository;

    @Autowired
    public TodoController(final TodoRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET, produces = "application/json")
    @Restrict
    public AllTodos allTodos(@RequestHeader("user-id") final UUID userId) {
        log.info("GET all todo's for user {}", userId);
        return  new AllTodos(repository.get(userId));
    }

    @RequestMapping(value = "/me/{todo}", method = RequestMethod.GET, produces = "application/json")
    @Restrict
    public ResponseEntity<TodoList> todoList(@RequestHeader("user-id") final UUID userId, @PathVariable("todo") final String todoList) {
        log.info("GET todo {} for user {}", todoList, userId);
        return  repository
                .get(userId, todoList)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/me", method = RequestMethod.DELETE)
    @Restrict
    public Callable<ResponseEntity<?>> deleteAll(@RequestHeader("user-id") final UUID userId) {
        log.info("DELETE all todo's for user {}", userId);
        return () -> {
            repository.delete(userId);

            return ResponseEntity.accepted().build();
        };
    }

    @RequestMapping(value = "/me/{todo}", method = RequestMethod.DELETE)
    @Restrict
    public Callable<ResponseEntity<?>> deleteTodo(@RequestHeader("user-id") final UUID userId, @PathVariable("todo") final String todoList) {
        log.info("DELETE todo {} for user {}", todoList, userId);
        return () -> {
            repository.delete(userId, todoList);

            return ResponseEntity.accepted().build();
        };
    }

    @RequestMapping(value = "/me", method = RequestMethod.POST)
    @Restrict
    public Callable<ResponseEntity<Void>> createTodoList(@RequestHeader("user-id") final UUID userId, @RequestBody TodoList todoList) {
        return () -> {
            repository.add(userId, todoList);
            return ResponseEntity.accepted().build();
        };
    }
}
