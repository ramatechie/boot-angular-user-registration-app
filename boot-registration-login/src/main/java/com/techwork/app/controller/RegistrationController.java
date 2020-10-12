package com.techwork.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techwork.app.model.User;
import com.techwork.app.service.RegistartionService;

@RestController
@RequestMapping(value = "/api")
public class RegistrationController {
	
	@Autowired
	private RegistartionService service;
	
	@PostMapping(value = "/register")
	@CrossOrigin(origins = "http://localhost:4200")
	public User saveRegisterUser(@RequestBody User user) throws Exception {
		String tempEmailId= null;	
		tempEmailId = user.getEmailId();
		if(tempEmailId!=null && !"".equals(tempEmailId)) {
			User userFindByEmail = service.userFindByEmail(tempEmailId);
			if(userFindByEmail!=null) {
				throw new Exception("This User with " + tempEmailId + " Alredy Existed ");
			}
		}
		User userObject = null;
		userObject = service.saveUser(user);
		return userObject;

	}
	
	@PostMapping(value = "/login")
	@CrossOrigin(origins = "http://localhost:4200")
	public User loginUser(@RequestBody User user) throws Exception {

		String tempEmailId = user.getEmailId();
		String tempPassword = user.getPassword();
		User userFindByEmailAndPassword = null;
		if (tempEmailId != null && tempPassword != null) {
			userFindByEmailAndPassword = service.userFindByEmailAndPassword(tempEmailId, tempPassword);
		}
		if(userFindByEmailAndPassword == null) {
			throw new Exception(" Bad Credentials");
		}
		return userFindByEmailAndPassword;
	}

}
