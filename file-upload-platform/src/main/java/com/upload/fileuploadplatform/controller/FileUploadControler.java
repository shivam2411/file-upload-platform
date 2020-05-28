package com.upload.fileuploadplatform.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.upload.fileuploadplatform.model.User;
import com.upload.fileuploadplatform.repository.FileRepository;
import com.upload.fileuploadplatform.repository.UserRepository;
import com.upload.fileuploadplatform.service.AwsService;
import com.upload.fileuploadplatform.repository.UserRepository;

@RestController
public class FileUploadControler {
	@Autowired
	UserRepository userRepository;
	
	@Autowired 
	FileRepository fileRepository;
		
	
	@Autowired
	AwsService awsService;
	
	
	@PostMapping("/users")
	public void createAccount(@RequestBody User user)
	{
		userRepository.save(user);
		
	}
	
	
	
	@GetMapping("/uploaded")
	ArrayList<String> getUploadedFiles(Principal principal) {
		String currentUser = principal.getName();
		return userRepository.findByName(currentUser).getUploadedFiles();
	}
	
	@RequestMapping("/welcome")
	public String welcome()
	{
		return "Welcome to the file upload platform";
	}
	
	@PostMapping("/upload/{fileName}")
	public  String uploadFile(@PathVariable String fileName, Principal principal) {
		String currentUser = principal.getName();
		fileRepository.uploadFile(fileName, currentUser);
		return "File was uploaded successfully";
	}
	
	@PostMapping("/remove/{fileName}")
	public  String removeFile(@PathVariable String fileName, Principal principal) {
		String currentUser = principal.getName();
		fileRepository.removeFile(fileName, currentUser);
		return "File was removed successfully";
	}

	
    
	
	@PostMapping("/aws/upload")
	public  String awsBuckets(@RequestPart(value= "file") final MultipartFile multipartFile) {
		awsService.uploadFile(multipartFile);	
		
        
		return "File was uploaded successfully";
	}
	
	
	
	

}
