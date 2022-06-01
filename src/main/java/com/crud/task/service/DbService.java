package com.crud.task.service;

import com.crud.task.domain.Task;
import com.crud.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class DbService {
    private final TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }


    public Task saveTask(final Task task) {
        return repository.save(task);
    }

    @Transactional
    public void deleteTask(Long id) {
        repository.deleteTaskById(id);
    }

    public Task getTaskById(Long id) {
        Optional<Task> taskOptional = repository.findById(id);
        Task task = taskOptional.orElse(new Task(999L, "defoult", "default"));
        return task;
    }

}

