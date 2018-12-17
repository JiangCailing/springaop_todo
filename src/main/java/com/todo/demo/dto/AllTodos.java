package com.todo.demo.dto;

import com.todo.demo.model.TodoList;
import java.util.List;

public class AllTodos {
    private List<TodoList> todoLists;

    public AllTodos(List<TodoList> todoLists) {
        this.todoLists = todoLists;
    }

    public AllTodos() {
    }
    public List<TodoList> getTodoLists() {
        return todoLists;
    }

    public void setTodoLists(List<TodoList> todoLists) {
        this.todoLists = todoLists;
    }
    
    
}
