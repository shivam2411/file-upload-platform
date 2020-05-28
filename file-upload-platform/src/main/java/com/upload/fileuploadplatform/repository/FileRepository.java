package com.upload.fileuploadplatform.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.upload.fileuploadplatform.model.User;

@Repository
public class FileRepository {
	@Autowired
	UserRepository userRepository;
	
	public void uploadFile(String fileName, String currentUser) {
		User user = userRepository.findByName(currentUser);
		if(user.getUploadedFiles() != null)
		user.getUploadedFiles().add(fileName);
		else {
			ArrayList<String> files= new ArrayList<>();
			files.add(fileName);
			user.setUploadedFiles(files);
		}
		userRepository.save(user);
	}
	
	public void removeFile(String fileName, String currentUser) {
		User user = userRepository.findByName(currentUser);
		if(user.getUploadedFiles() == null)
		return;
		else {
			ArrayList<String> files= user.getUploadedFiles();
			files.remove(fileName);
			user.setUploadedFiles(files);
		}
		userRepository.save(user);
	}

}
