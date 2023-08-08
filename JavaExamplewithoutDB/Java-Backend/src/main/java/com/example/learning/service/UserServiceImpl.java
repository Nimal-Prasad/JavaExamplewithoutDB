package com.example.learning.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.learning.dto.LoginDTO;
import com.example.learning.dto.RegistrationDTO;
import com.example.learning.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService, UserRepository {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public String userRegistration(RegistrationDTO registrator) {
		log.info("Registration Request Service Processing");
		if(USER_DETAILS.containsKey(registrator.getEmailId())) {
			return "User Already Exists";
		}
		else{
			USER_DETAILS.put(registrator.getEmailId(), registrator.getUserId()+":"+registrator.getPass());
			return "User Registered Successfully";
		}
	}

	@Override
	public String userLogin(LoginDTO logger) {
		log.info("Logging Request Service Processing");
		if(USER_DETAILS.containsKey(logger.getId())) {
			String[] temp = USER_DETAILS.get(logger.getId()).split(":");
			if(temp[1].equalsIgnoreCase(logger.getPass())) {
				return "Logged In Successfully";
			}
		}
		return "User Does Not Exists";
	}

	@Override
	public List<Map<String, String>> readAllUsers() {
		List<Map<String, String>> users = new ArrayList<>();
		for(Map.Entry<String, String> userDetails : USER_DETAILS.entrySet()) {
			Map<String, String> tempStore = new HashMap<>();
			tempStore.put("emailId", userDetails.getKey());
			tempStore.put("userId", userDetails.getValue().split(":")[0]);
			tempStore.put("pass", userDetails.getValue().split(":")[1]);
			users.add(tempStore);
		}
		return users;
	}

	@Override
	public void deleteUser(String userId) {
		USER_DETAILS.remove(userId);
	}

}
