package com.example.demo.model;

public class UserRole {
	 
    private String name;
    private ERole role; //admin, user
    private boolean isActive;
    
	public UserRole(String name, ERole role, boolean isActive) {
		super();
		this.name = name;
		this.role = role;
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ERole getRole() {
		return role;
	}

	public void setRole(ERole role) {
		this.role = role;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

    
 
}
