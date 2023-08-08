package com.example.learning.service;

import java.util.List;
import java.util.Map;

import com.example.learning.dto.LoginDTO;
import com.example.learning.dto.RegistrationDTO;

public interface UserService {

	public String userRegistration(RegistrationDTO registrator);
	
	public String userLogin(LoginDTO logger);
	
	public List<Map<String, String>> readAllUsers();
	
	public void deleteUser(String userId);
	
}
