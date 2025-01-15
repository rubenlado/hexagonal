package com.ruben.hexagonal.infrastructure.adapters.output.persistence;

import com.ruben.hexagonal.application.ports.out.TaskRepositoryPort;
import com.ruben.hexagonal.domain.models.Task;
import com.ruben.hexagonal.infrastructure.adapters.input.rest.request.TaskRequest;
import com.ruben.hexagonal.infrastructure.adapters.output.persistence.entity.TaskEntity;
import com.ruben.hexagonal.infrastructure.adapters.output.persistence.mapper.TaskPersistenceMapper;
import com.ruben.hexagonal.infrastructure.adapters.output.persistence.repository.TaskRepository;
import org.springframework.beans.BeanUtils;
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
    public List<Task> findTask(Optional<String> search) {
        return taskRepository.findByTitle(search).stream()
                .map(taskPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }



    @Override
    public Task save(Task task) {
        TaskEntity taskEntity = taskPersistenceMapper.toEntity(task);
        TaskEntity savedEntity = taskRepository.save(taskEntity);
        return taskPersistenceMapper.toDomain(savedEntity);

    }

    @Override
    public Optional<Task> findById(Long id) {
        Optional<TaskEntity> foundEntity = taskRepository.findById(id);
        return foundEntity.map(taskPersistenceMapper::toDomain);
    }


    @Override
    public Optional<Task> update(Long id, TaskRequest task) {
        Optional<TaskEntity> foundEntity = taskRepository.findById(id);

        if (foundEntity.isPresent()) {
            TaskEntity entity = foundEntity.get();

            try {
                if (task.isCompleted()) {
                    entity.setCompleted(true); // Set completed to true
                }

                if (task.getTitle() != null) {
                    entity.setTitle(task.getTitle());
                }
                if (task.getDescription() != null) {
                    entity.setDescription(task.getDescription());
                }

                TaskEntity updatedEntity = taskRepository.save(entity);

                return Optional.of(taskPersistenceMapper.toDomain(updatedEntity));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return Optional.empty();
    }

    public boolean deleteById(Long id) {
        Optional<TaskEntity> foundEntity = taskRepository.findById(id);

        if (foundEntity.isPresent()) {
            taskRepository.deleteById(id);
            return true;
        }

        return false;
    }


}