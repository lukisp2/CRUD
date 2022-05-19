package com.crud.task.domain.controller;

import com.crud.task.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    @GetMapping
    public TaskDto getTask(Long taskId) {
        return new TaskDto(1L, "Task title", "Task content");
    }

    @GetMapping
    public List<TaskDto> getTask() {
        return new ArrayList<>();
    }

    @DeleteMapping
    public void deleteTask(Long taskId) {

    }

    @PutMapping
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "Updated task", "Updated task content");
    }

    @PostMapping
    public void createTask(TaskDto taskDto) {

    }

}
