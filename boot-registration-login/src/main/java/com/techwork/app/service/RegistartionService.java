package com.techwork.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techwork.app.model.User;
import com.techwork.app.repository.RegistrationRepository;

@Service
public class RegistartionService {
	
	@Autowired
	private RegistrationRepository repository;
	
	public User saveUser(User user) {
		User savedUser = repository.save(user);
		return savedUser;
	}
	
	public User userFindByEmail(String email) {
		return repository.findByEmailId(email);
	}
	
	public User userFindByEmailAndPassword(String email, String password) {
		return repository.findByEmailIdAndPassword(email, password);
	}

}
