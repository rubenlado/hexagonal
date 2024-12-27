package com.ruben.hexagonal.application.ports.in;

import com.ruben.hexagonal.domain.models.Task;

import java.util.List;

public interface TaskServicePort {
    List<Task> findAll();
}
