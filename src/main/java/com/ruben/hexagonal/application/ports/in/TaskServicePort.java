package com.ruben.hexagonal.application.ports.in;

import com.ruben.hexagonal.domain.models.Task;
import com.ruben.hexagonal.infrastructure.adapters.input.rest.request.TaskRequest;

import java.util.List;
import java.util.Optional;

public interface TaskServicePort {
    Optional<Task> findTaskDetail(Long id);
    List<Task> findTask(Optional<String> search);
    Task createTask(Task task);
    Optional<Task> updateTask(Long id, TaskRequest task);
    boolean deleteTask(Long id);

}
