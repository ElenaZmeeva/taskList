package com.example.tasklist.service;

import com.example.tasklist.Status;
import com.example.tasklist.dto.TaskDto;
import com.example.tasklist.entity.Task;
import com.example.tasklist.mapper.TaskMapper;
import com.example.tasklist.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final static Logger logger= LoggerFactory.getLogger(TaskService.class);
    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    public TaskService(TaskMapper taskMapper, TaskRepository taskRepository) {
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
    }

    public TaskDto getTaskDto(Long id) {
        Task task1=taskRepository.findById(id).orElse(null);
        return taskMapper.toDto(task1);
    }

    public TaskDto addTask(TaskDto taskDto){
       Task task1=taskMapper.toEntity(taskDto);
       taskRepository.save(task1);
       logger.info("Task added");
       return taskMapper.toDto(task1);
    }

    public TaskDto updateTask(TaskDto taskDto, Long id){
        Task task1=taskRepository.findById(id).orElse(null);
        assert task1 != null;
        task1.setTitle(taskDto.getTitle());
        task1.setDescription(taskDto.getDescription());
        logger.info("Task updated");
        return taskMapper.toDto(task1);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
        logger.info("Task deleted");
    }

    public List<Task> getAllTaskByUser(String fullName){
        return taskRepository.findAllByUser(fullName);
    }

    public List<Task> getAllTaskByUserAndLabel (String fullName, Status status){
        return taskRepository.findAllByUserAndStatus(fullName, status);
    }
}
