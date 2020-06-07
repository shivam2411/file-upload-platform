package com.upload.fileuploadplatform.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
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
	public String welcome() {
		return "Welcome to the file upload platform";
	}

	@PostMapping("/users")
	public String createAccount(@RequestBody User user) {
		return userService.save(user);
		

	}
	
	@PostMapping("/aws/upload")
	public String awsBucketUpload(@RequestPart(value = "file") final MultipartFile multipartFile, Principal principal) {

		return awsService.uploadFile(multipartFile, principal);

	}
	
	@PostMapping("/aws/remove/{fileId}")
	public String awsBucketRemove(@PathVariable String fileId, Principal principal) {

		return awsService.removeFile(fileId, principal);
	}

	@GetMapping("/uploaded")
	ArrayList<FileMetaData> getUploadedFilesList(Principal principal) {

		return fileService.getUploadedFilesList(principal);

	}

	@PostMapping("/update/{fileId}")
	public String updateFileMetaData(@RequestBody FileMetaData fileMetaData, @PathVariable String fileId,
			Principal principal) {
		return fileService.updateFileMetaData(fileMetaData, fileId, principal);
	}

	// Method for debugging purposes
	@GetMapping("/users/{emailAddress}")
	public User getUserDetails(@PathVariable String emailAddress) {

		return userService.getUserDetails(emailAddress);
	}

}
