package com.project.todolist;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {
    @Id            //https://github.com/bitseatY/simple-to-do-list.git
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String task_Content;
    private String state;

    public Task(){}
    public Task(String task_Content){
        this.task_Content= task_Content;
    }
    public int getId(){
        return id;
    }
    public String getTaskContent(){
        return task_Content;
    }
    public String getState(){
        return state;
    }
    public void setTaskContent(String task_Content){
        this.task_Content= this.task_Content;
    }
    public void setState(String state){
        this.state=state;
    }


}
