package com.upload.fileuploadplatform.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class User {
	
	//@Indexed(unique = true)
	@Id
	private String emailAddress;
	private String password;
	 private ArrayList<FileMetaData> uploadedFiles;
	
	 User() {
		super();
	}

	public User(User user) {
		super();
		this.emailAddress = user.emailAddress;
		this.password = user.password;
		this.uploadedFiles = user.uploadedFiles;
	}
	public User(String emailAddress, String password, ArrayList<FileMetaData> uploadedFiles) {
		super();
		this.emailAddress = emailAddress;
		this.password = password;
		this.uploadedFiles = uploadedFiles;
	}
	
	
	
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setUploadedFiles(ArrayList<FileMetaData> uploadedFiles) {
		this.uploadedFiles = uploadedFiles;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		this.password = encoder.encode(password);
	}
	public ArrayList<FileMetaData> getUploadedFiles() {
		return uploadedFiles;
	}
	

}
