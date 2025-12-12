package com.project.todolist;
import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Services {
    @Autowired
    private TaskRepo taskRepo;

    public Task saveTask(Task task){
        return taskRepo.save(task);
    }
    public  void  checkOffTask(int id){
         Task task=taskRepo.findById(id).orElseThrow();
         task.setState("completed");
         taskRepo.save(task);

    }
    public List<Task> listTasks(){
       return   taskRepo.findAll();
    }
    public List<Task> removeTask(int id){
       taskRepo.deleteById(id);
       return taskRepo.findAll();
    }

}

