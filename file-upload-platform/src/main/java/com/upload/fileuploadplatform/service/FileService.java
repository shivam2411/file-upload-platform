package com.upload.fileuploadplatform.service;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upload.fileuploadplatform.model.FileMetaData;
import com.upload.fileuploadplatform.model.User;
import com.upload.fileuploadplatform.repository.FileRepository;
import com.upload.fileuploadplatform.repository.UserRepository;

@Service
public class FileService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	FileRepository fileRepository;

	public ArrayList<FileMetaData> getUploadedFilesList(Principal principal) {

		String currentUser = principal.getName();
		User user = userRepository.findByEmailAddress(currentUser);
		return user.getUploadedFiles();

	}

	public void updateFileMetaData(FileMetaData fileInfo, String fileId, Principal principal) {

		String emailAddress = principal.getName();
		fileRepository.updateFileMetaData(fileInfo, emailAddress, fileId);

	}

	public String removeFileMetaData(String fileId, Principal principal) {

		String emailAddress = principal.getName();
		fileRepository.removeFileMetaData(emailAddress, fileId);
		return "File meta data was removed successfully";

	}

}
