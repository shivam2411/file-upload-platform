package com.upload.fileuploadplatform.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.upload.fileuploadplatform.model.FileMetaData;
import com.upload.fileuploadplatform.model.User;
import com.upload.fileuploadplatform.repository.FileRepository;
import com.upload.fileuploadplatform.repository.UserRepository;
import com.upload.fileuploadplatform.service.AwsService;
import com.upload.fileuploadplatform.service.FileService;
import com.upload.fileuploadplatform.service.UserService;

@Controller
public class FileUploadControler {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@Autowired
	FileService fileService;

	@Autowired
	FileRepository fileRepository;

	@Autowired
	AwsService awsService;

	@RequestMapping("/welcome")
	public String welcome(Model model) {
		model.addAttribute("welcome", "Welcome to the file upload platform");
		return "welcome";
	}

	@PostMapping("/users")
	public String createAccount( User user, Model model) {
		
		 userService.save(user);
		 return "login";
		

	}
	
	@PostMapping("/aws/upload")
	public String awsBucketUpload(@RequestPart(value = "file") final MultipartFile multipartFile, Principal principal, Model model) {
		model.addAttribute("uploadResult", awsService.uploadFile(multipartFile, principal));
		return "uploadResult";
		

	}
	
	@PostMapping("/aws/remove/{fileId}")
	public String awsBucketRemove( String fileId, Principal principal, Model model) {
		model.addAttribute("removeResult", awsService.removeFile(fileId, principal));
		return "removeResult";
	}

	@GetMapping("/uploaded")
	String getUploadedFilesList(Model model, Principal principal) {
		model.addAttribute("uploaded", fileService.getUploadedFilesList(principal) );
		return "uploaded";

	}

	@PostMapping("/update/{fileId}")
	public String updateFileMetaData( FileMetaData fileMetaData,  String fileId,
			Principal principal, Model model) {
		 fileService.updateFileMetaData(fileMetaData, fileId, principal);
		 model.addAttribute("uploaded", fileService.getUploadedFilesList(principal) );
			return "uploaded";
	}
	

	@GetMapping("/upload")
	public String uploadFile(Model model) {

		return "uploadFile";
	}
	
	@GetMapping("/update")
	public String updateMetaData(Model model) {

		return "updateMetaData";
	}
	
	@GetMapping("/remove")
	public String remove(Model model) {

		return "remove";
	}
	
	@GetMapping("/signup")
	public String signUp(Model model) {

		return "users";
	}
	
	
	// Method for debugging purposes
	@GetMapping("/users/{emailAddress}")
	public User getUserDetails(@PathVariable String emailAddress) {

		return userService.getUserDetails(emailAddress);
	}

}
