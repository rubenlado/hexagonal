package com.ruben.hexagonal.domain.models;

public class User {
    private final Long id;
    private final String name;
    private final String email;

    public User(Long userId, String userName, String userEmail) {
        this.id = userId;
        this.name = userName;
        this.email = userEmail;
    }

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

  
}