package com.upload.fileuploadplatform.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class User {
	public User() {
		super();
	}

	public User(User user) {
	name = user.name;
	password = user.password;
	uploadedFiles = user.uploadedFiles;
	}
	public User(String name, String password, ArrayList<String> uploadedFiles) {
		super();
		this.name = name;
		this.password = password;
		this.uploadedFiles = uploadedFiles;
	}
	
	@Id
	String userId;
	
	String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		this.password = encoder.encode(password);
	}
	public ArrayList<String> getUploadedFiles() {
		return uploadedFiles;
	}
	public void setUploadedFiles(ArrayList<String> uploadedFiles) {
		this.uploadedFiles = uploadedFiles;
	}
	String password;
	ArrayList<String> uploadedFiles;

}
