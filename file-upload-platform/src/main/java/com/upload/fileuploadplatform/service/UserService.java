package com.upload.fileuploadplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upload.fileuploadplatform.model.User;
import com.upload.fileuploadplatform.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public void save(User user) {
		
		userRepository.save(user);
		
		
	}
	
	public User getUserDetails(String emailAddress) {
		
		return userRepository.findByEmailAddress(emailAddress);
		
	}

}
