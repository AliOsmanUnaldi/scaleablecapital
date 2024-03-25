package com.codility.scaleablecapital.controller;

import com.codility.scaleablecapital.constants.TaskStatus;
import com.codility.scaleablecapital.entity.Task;
import com.codility.scaleablecapital.entity.TaskDto;
import com.codility.scaleablecapital.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.lang.IllegalArgumentException;
import java.util.*;

@RestController
@RequestMapping("/")
public class TaskController {
    private final TaskRepository repository;

    @Autowired
    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @PostMapping("tasks")
    public Long add(@RequestParam String title, @RequestParam String description){
        Task task = new Task(title);
        task.setDescription(description);
        repository.save(task);
        return task.getId();
    }

    @GetMapping("tasks/{id}")
    public TaskDto read(@PathVariable Long id){
        Task task = repository.findById(id).orElse(null);
        if(task == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Task not found");
        }
        return task.toDto();
    }


   @PutMapping("tasks/{id}")
    public void update(@PathVariable Long id, @RequestBody TaskDto dto){
        Task oldTask = repository.findById(id).orElse(null);
        if(oldTask == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Task not found");
        }
        try{
            oldTask.setTaskStatus(TaskStatus.valueOf(dto.getTaskStatus()));
        } catch(IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status given");
        }
        oldTask.setTitle(dto.getTitle());
        oldTask.setDescription(dto.getDescription());
        repository.save(oldTask);
    }

    @DeleteMapping("tasks/{id}")
    public void delete (@PathVariable Long id){
        Task task = repository.findById(id).orElse(null);
        if(task == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Task not found");}
        repository.deleteById(id);
    }

    @GetMapping("tasks")
    public List<TaskDto> findAll(){
        Iterable<Task> tasks = repository.findAll();
        List<TaskDto> dtos = new ArrayList<>();
        for(Task task : tasks){
            dtos.add(task.toDto());
        }
        return dtos;
    }
}