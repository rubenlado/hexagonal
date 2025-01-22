package com.ruben.hexagonal.application.ports.in;

import com.ruben.hexagonal.domain.models.Task;
import com.ruben.hexagonal.infrastructure.adapters.input.rest.request.TaskRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface TaskServicePort {
    Mono<Task> findTaskDetail(Long id);
    Mono<List<Task>> findTask(Optional<String> search);
    Mono<Task> createTask(Task task);
    Mono<Task> updateTask(Long id, TaskRequest task);
    Mono<Boolean> deleteTask(Long id);
    Flux<Task> getCompletedTasks();
}
