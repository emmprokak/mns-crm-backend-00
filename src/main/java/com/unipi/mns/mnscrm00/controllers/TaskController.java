package com.unipi.mns.mnscrm00.controllers;

import com.unipi.mns.mnscrm00.dto.abstracts.TaskDTO;
import com.unipi.mns.mnscrm00.entities.data.Task;
import com.unipi.mns.mnscrm00.services.concretes.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{id}")
    public TaskDTO getTask(@PathVariable String id){
        return taskService.getTaskByIdSimple(id);
    }

    @GetMapping("/{id}/complete")
    public TaskDTO getTaskWithRels(@PathVariable String id){
        return taskService.getTaskByIdComplete(id);
    }

    @PostMapping("/new")
    public TaskDTO createTask(@RequestBody Task task){
        return taskService.insertTask(task);
    }

    @GetMapping("/all")
    public List<TaskDTO> getTasks(){
        return taskService.getAllTasks();
    }

    @PutMapping("/{id}")
    public TaskDTO updateTask(@PathVariable String id, @RequestBody Task task){
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTask(@PathVariable String id){
        return taskService.deleteTaskById(id);
    }
}
