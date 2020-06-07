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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.upload.fileuploadplatform.model.FileMetaData;
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
		try {
		userRepository.save(user);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	@GetMapping("/users/{emailAddress}")
	public  User getUser(@PathVariable String emailAddress) {
		return userRepository.findByEmailAddress(emailAddress);
	}
	
	
	
	@GetMapping("/uploaded")
	ArrayList<FileMetaData> getUploadedFiles(Principal principal) {
		String currentUser = principal.getName();
		User user = userRepository.findByEmailAddress(currentUser);
		return user.getUploadedFiles();
	}
	
	@RequestMapping("/welcome")
	public String welcome()
	{
		return "Welcome to the file upload platform";
	}
	
	@PostMapping("/upload/{fileId}")
	public  String uploadFile(@RequestBody FileMetaData fileInfo, @PathVariable String fileId, 
			Principal principal) {
		String emailAddress = principal.getName();
		fileRepository.uploadFile(fileInfo, emailAddress, fileId);
		return "File was uploaded successfully";
	}
	
	@PostMapping("/remove/{fileId}")
	public  String removeFile(@PathVariable String fileId, Principal principal) {
		String currentUser = principal.getName();
		fileRepository.removeFile(fileId, currentUser);
		return "File was removed successfully";
	}

	
    
	
	@PostMapping("/aws/upload")
	public  String awsBucketUpload(@RequestPart(value= "file") final MultipartFile multipartFile, Principal principal) {
		String emailAddress = principal.getName();
		String fileId=  awsService.uploadFile(multipartFile, emailAddress);	
		
        
		return "File was uploaded successfully with file id as " + fileId;
	}
	
	@PostMapping("/aws/remove/{fileId}")
	public  String awsBucketRemove(@PathVariable String fileId,  Principal principal) {
		String emailAddress = principal.getName();
		awsService.removeFile(fileId, emailAddress);	
		
        
		return "File was uploaded successfully";
	}
	
	
	
	

}
