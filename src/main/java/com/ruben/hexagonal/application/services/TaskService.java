package com.ruben.hexagonal.application.services;

import com.ruben.hexagonal.application.ports.in.TaskServicePort;
import com.ruben.hexagonal.application.ports.out.TaskRepositoryPort;
import com.ruben.hexagonal.domain.models.Task;
import com.ruben.hexagonal.infrastructure.adapters.input.rest.request.TaskRequest;
import com.ruben.hexagonal.infrastructure.adapters.input.rest.request.TaskSearchCriteria;
import com.ruben.hexagonal.infrastructure.adapters.input.rest.response.PagedResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements TaskServicePort {

    private final TaskRepositoryPort repositoryPort;

    public TaskService(TaskRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Mono<Task> findTaskDetail(Long id) {
        return repositoryPort.findById(id);
    }

    @Override
    public Mono<PagedResponse<Task>> findTask(TaskSearchCriteria criteria) {
        return repositoryPort.findTaskPaged(criteria);
    }

    @Override
    public Mono<List<Task>> findAllTasks(Optional<String> search ) {
        return repositoryPort.findTask(search);
    }

    @Override
    public Mono<Task> createTask(Task task) {
        return repositoryPort.save(task);
    }

    @Override
    public Mono<Task> updateTask(Long id, TaskRequest task){ return repositoryPort.update(id, task);}

    @Override
    public Mono<Boolean> deleteTask(Long id){ return repositoryPort.deleteById(id);}

    @Override
    public Flux<Task> getCompletedTasks() {
        return repositoryPort.findCompleted();
    };
}
