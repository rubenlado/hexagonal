package com.ruben.hexagonal.domain.models;

import java.time.LocalDateTime;

public class Task {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private boolean completed;

    public Task(Long id, String title, String description, LocalDateTime creationDate, boolean completed) {
        this.setId(id);
        this.setTitle(title);
        this.setDescription(description);
        this.setCreationDate(creationDate);
        this.setCompleted(completed);
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}