package com.ruben.hexagonal.application.ports.out;
import com.ruben.hexagonal.domain.models.Task;
import com.ruben.hexagonal.infrastructure.adapters.input.rest.request.TaskRequest;

import java.util.List;
import java.util.Optional;

public interface TaskRepositoryPort {
    Task save(Task task);
    Optional<Task> findById(Long id);
    List<Task> findTask(Optional<String> search);
    Optional<Task> update(Long id, TaskRequest task);
    boolean deleteById(Long id);
}
