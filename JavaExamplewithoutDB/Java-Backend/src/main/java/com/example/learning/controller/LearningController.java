package com.example.learning.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.learning.dto.LoginDTO;
import com.example.learning.dto.RegistrationDTO;
import com.example.learning.service.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class LearningController {
	
	@Autowired
	private UserService userService;
	
	private static final Logger log = LoggerFactory.getLogger(LearningController.class);
	
	@PostMapping("/register")
	public ResponseEntity<?> userRegistration(@Valid @RequestBody RegistrationDTO registrator) {
		log.info("Registration Request Controller Start");
		Map<String, Object> response = Map.of("result",userService.userRegistration(registrator));
		log.info("Registration Request Controller End");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> userLogin(@Valid @RequestBody LoginDTO logger) {
		log.info("Logging Request Controller Start");
		Map<String, Object> response = Map.of("result",userService.userLogin(logger));
		log.info("Logging Request Controller End");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers() {
		log.info("GetAllUsers Request Controller Start");
		List<Map<String, String>> response = userService.readAllUsers();
		log.info("GetAllUsers Request Controller End");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable String userId) {
		log.info("DeleteUser Request Controller Start");
		userService.deleteUser(userId);
		log.info("DeleteUser Request Controller End");
		return new ResponseEntity<Object>(Map.of("result","Deleted SuccessFully"), HttpStatus.OK);
	}
}
