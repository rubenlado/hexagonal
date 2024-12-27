// TaskRepository interface
package com.ruben.hexagonal.infrastructure.adapters.output.persistence.repository;

import com.ruben.hexagonal.infrastructure.adapters.output.persistence.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}