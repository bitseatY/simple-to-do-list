package com.project.todolist;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String taskContent;
    private String state="pending";

    public Task(){}
    public Task(String taskContent){
        this.taskContent= taskContent;
    }
    public int getId(){
        return id;
    }
    public String getTaskContent(){
        return taskContent;
    }
    public String getState(){
        return state;
    }
    public void setTaskContent(String taskContent){
        this.taskContent= this.taskContent;
    }
    public void setState(String state){
        this.state=state;
    }


}
