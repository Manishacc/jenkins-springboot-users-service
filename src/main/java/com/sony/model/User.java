package com.sony.model;

import java.util.UUID;

import lombok.Data;

@Data
public class User {
	private String id;
	private String firstname;
	private String lastname;
	private String email;

	public User() {
		this.id= UUID.randomUUID().toString();
	}

	public User(String firstname, String lastname, String email) {
		this();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}
	
}
