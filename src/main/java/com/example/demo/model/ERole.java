package com.example.demo.model;

public enum ERole {
	ADMIN("admin"),
	USER("user");
    public final String userName;

	private ERole(String name) {
		this.userName = name;
	}
 
}
