package com.example.learning.dto;

import jakarta.validation.constraints.NotBlank;

public class RegistrationDTO {

	@NotBlank(message = "userId must not be null")
	private String userId;
	
	@NotBlank(message = "emailId must not be null")
	private String emailId;
	
	@NotBlank(message = "pass must not be null")
	private String pass;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
