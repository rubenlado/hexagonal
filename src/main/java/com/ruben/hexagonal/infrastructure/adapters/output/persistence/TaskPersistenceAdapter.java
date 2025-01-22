package com.ruben.hexagonal.infrastructure.adapters.output.persistence;

import com.ruben.hexagonal.application.ports.out.TaskRepositoryPort;
import com.ruben.hexagonal.domain.models.Task;
import com.ruben.hexagonal.infrastructure.adapters.input.rest.request.TaskRequest;
import com.ruben.hexagonal.infrastructure.adapters.output.persistence.entity.TaskEntity;
import com.ruben.hexagonal.infrastructure.adapters.output.persistence.mapper.TaskPersistenceMapper;
import com.ruben.hexagonal.infrastructure.adapters.output.persistence.repository.TaskRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TaskPersistenceAdapter implements TaskRepositoryPort {

    private final TaskRepository taskRepository;
    private final TaskPersistenceMapper taskPersistenceMapper;

    public TaskPersistenceAdapter(TaskRepository taskRepository, TaskPersistenceMapper taskPersistenceMapper) {
        this.taskRepository = taskRepository;
        this.taskPersistenceMapper = taskPersistenceMapper;
    }

    @Override
    public Mono<List<Task>> findTask(Optional<String> search) {
        return taskRepository.findByTitle(search.orElse(null))
                .collectList()
                .map(taskEntities ->
                        taskEntities.stream()
                                .map(taskPersistenceMapper::toDomain)
                                .collect(Collectors.toList())
                );
    }

    @Override
    public Mono<Task> save(Task task) {
        TaskEntity taskEntity = taskPersistenceMapper.toEntity(task);
        return taskRepository.save(taskEntity).map(taskPersistenceMapper :: toDomain);
    }

    @Override
    public Mono<Task> findById(Long id) {
        return taskRepository.findById(id).map(taskPersistenceMapper::toDomain);
    }


    @Override
    public Mono<Task> update(Long id, TaskRequest taskRequest) {
        return taskRepository.findById(id)
                .flatMap(entity -> {
                    if (taskRequest.isCompleted()) {
                        entity.setCompleted(true);
                    }

                    if (taskRequest.getTitle() != null) {
                        entity.setTitle(taskRequest.getTitle());
                    }
                    if (taskRequest.getDescription() != null) {
                        entity.setDescription(taskRequest.getDescription());
                    }

                    return taskRepository.save(entity)
                            .map(taskPersistenceMapper::toDomain);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Boolean> deleteById(Long id) {
        return taskRepository.findById(id)
                .flatMap(taskEntity -> taskRepository.deleteById(id)
                            .then(Mono.just(true))
                )
                .defaultIfEmpty(false);
    }


    @Override
    public Flux<Task> findCompleted() {
        return taskRepository.findCompleted()
                .map(taskPersistenceMapper::toDomain);
    }

}