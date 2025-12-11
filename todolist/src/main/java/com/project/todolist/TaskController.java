package com.project.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {
     @Autowired
     private Services services;

     @GetMapping("/read")
     public Task getTask(@RequestParam int id) {
         return services.getTask(id);
     }
     @PostMapping("/write")
    public Task addTask(@RequestBody Task task){
         return services.saveTask(task);
     }

}
