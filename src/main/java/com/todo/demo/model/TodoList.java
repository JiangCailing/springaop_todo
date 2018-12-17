package com.todo.demo.model;


import java.util.List;

public class TodoList {
    private String name;
    private List<Todo> todos;

    public TodoList(String name, List<Todo> todos) {
        this.name = name;
        this.todos = todos;
    }

    public TodoList() {
       
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
    
    
}
