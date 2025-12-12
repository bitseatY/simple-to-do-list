package com.project.todolist;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TaskRepo extends JpaRepository<Task,Integer>{

}
