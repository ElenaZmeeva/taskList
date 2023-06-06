package com.example.tasklist.controller;

import com.example.tasklist.Status;
import com.example.tasklist.dto.TaskDto;
import com.example.tasklist.entity.Task;
import com.example.tasklist.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDto> addTask(@RequestBody TaskDto taskDto){
        TaskDto taskDto1= taskService.addTask(taskDto);
        return ResponseEntity.ok(taskDto1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("id") Long id){
        TaskDto taskDto=taskService.getTaskDto(id);
        if (taskDto==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(taskDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto task,@PathVariable("id") Long id){
        TaskDto taskDto=taskService.updateTask(task,id);
        if (taskDto==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(taskDto);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteTask(@RequestParam Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all/id")
    public ResponseEntity<List<Task>> getAllTaskByUser (@RequestParam String fullName){
        List<Task> myTask= taskService.getAllTaskByUser(fullName);
        return ResponseEntity.ok(myTask);
}
    @GetMapping("/all/id/status")
    public ResponseEntity<List<Task>> getAllTaskByUserAndLabel(@RequestParam String fullName,
                                                               @RequestParam Status status){
        List<Task> myTask= taskService.getAllTaskByUserAndLabel(fullName,status);
        return ResponseEntity.ok(myTask);
    }
}
