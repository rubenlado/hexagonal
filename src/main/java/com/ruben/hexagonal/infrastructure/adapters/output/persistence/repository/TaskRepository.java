// TaskRepository interface
package com.ruben.hexagonal.infrastructure.adapters.output.persistence.repository;

import com.ruben.hexagonal.infrastructure.adapters.output.persistence.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Query("SELECT t FROM TaskEntity t WHERE :search IS NULL OR UPPER(t.title) LIKE CONCAT('%', UPPER(:search), '%')")
    List<TaskEntity> findByTitle(@Param("search") Optional<String> search);
}