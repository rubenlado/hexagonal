package com.ruben.hexagonal.infrastructure.adapters.output.persistence;

import com.ruben.hexagonal.application.ports.out.TaskRepositoryPort;
import com.ruben.hexagonal.domain.models.Task;
import com.ruben.hexagonal.infrastructure.adapters.output.persistence.mapper.TaskPersistenceMapper;
import com.ruben.hexagonal.infrastructure.adapters.output.persistence.repository.TaskRepository;
import org.springframework.stereotype.Component;

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
    public List<Task> findAll() {
        return taskRepository.findAll().stream()
                .map(taskPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }



    @Override
    public Task save(Task task) {
        return null;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.empty();
    }


    @Override
    public Optional<Task> update(Task task) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }


}