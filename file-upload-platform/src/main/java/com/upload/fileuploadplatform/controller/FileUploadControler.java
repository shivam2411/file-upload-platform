package com.upload.fileuploadplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upload.fileuploadplatform.model.User;
import com.upload.fileuploadplatform.repository.UserRepository;
import com.upload.fileuploadplatform.repository.UserRepository;

@RestController
public class FileUploadControler {
	@Autowired
	UserRepository userRepository;
	
	
	
	@PostMapping("/users")
	public void createAccount(@RequestBody User user)
	{
		userRepository.save(user);
		
	}
	
	
	
	@GetMapping("/user/{name}")
	User getUser( @PathVariable String name) {
		return userRepository.findByName(name);
	}
	
	@RequestMapping("/welcome")
	public String welcome()
	{
		return "Welcome to the file upload platform";
	}
	
	@GetMapping("/upload")
	public  String uploadFile() {
		return "File was uploaded successfully";
	}
	
	
	
	

}
