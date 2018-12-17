package com.todo.demo.model;


public class Todo {
    private String task;
    private String time;
    
    public Todo(String task, String time) {
        this.task = task;
        this.time = time;
    }

    public Todo() {
    }
    
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

   
}
