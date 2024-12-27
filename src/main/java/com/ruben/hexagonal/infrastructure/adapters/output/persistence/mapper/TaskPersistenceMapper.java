package com.ruben.hexagonal.infrastructure.adapters.output.persistence.mapper;

import com.ruben.hexagonal.domain.models.Task;
import com.ruben.hexagonal.infrastructure.adapters.output.persistence.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskPersistenceMapper {

    public Task toDomain(TaskEntity taskEntity) {
        if (taskEntity == null) {
            return null;
        }

        return new Task(
                taskEntity.getId(),
                taskEntity.getTitle(),
                taskEntity.getDescription(),
                taskEntity.getCreationDate(),
                taskEntity.isCompleted()
        );
    }

    public TaskEntity toEntity(Task task) {
        if (task == null) {
            return null;
        }

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(task.getId());
        taskEntity.setTitle(task.getTitle());
        taskEntity.setDescription(task.getDescription());
        taskEntity.setCreationDate(task.getCreationDate());
        taskEntity.setCompleted(task.isCompleted());
        return taskEntity;
    }
}