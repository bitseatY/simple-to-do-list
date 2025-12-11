package com.project.todolist;
import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Service;
@Service
public class Services {
    @Autowired
    private TaskRepo taskRepo;
    public Task getTask(int id){
        Task task= taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return task;

    }
    public Task saveTask(Task task){
        return taskRepo.save(task);
    }

}

