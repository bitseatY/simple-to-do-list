package com.project.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
     @Autowired
     private Services services;
     @PostMapping("/saveTask")
      public  Task  saveTask(@RequestBody Task task){
         return services.saveTask(task);

     }
     @PutMapping("/checkOff")
     public void  checkOffTask(@RequestParam int id){
         services.checkOffTask(id);

     }
     @GetMapping ("/listAll")
     public List<Task> listTasks(){
       return   services.listTasks();
     }
     @DeleteMapping("/remove")
     public  List<Task> removeTask(@RequestParam int id){
         return services.removeTask(id);

     }





}
