package com.ruben.hexagonal.infrastructure.adapters.output.persistence.repository;
import com.ruben.hexagonal.infrastructure.adapters.output.persistence.entity.TaskEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TaskRepository extends ReactiveCrudRepository<TaskEntity, Long> {

    @Query("SELECT * FROM task t WHERE (:search IS NULL OR UPPER(t.title) LIKE CONCAT('%', UPPER(:search), '%'))")
    Flux<TaskEntity> findByTitle(String search);

    @Query("SELECT * FROM task WHERE completed = true")
    Flux<TaskEntity> findCompleted();
}