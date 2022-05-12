package com.crud.task.service;

import com.crud.task.domain.Task;
import com.crud.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.table.TableStringConverter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DbService {
    private final TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTaskById(Long id) {
        Optional<Task> taskOptional = repository.findById(id);
        Task task = taskOptional.orElse(new Task(999L, "defoult", "default"));
        return task;
    }

}

