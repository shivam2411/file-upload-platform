package com.upload.fileuploadplatform.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.upload.fileuploadplatform.exception.MetaDataUpdateException;
import com.upload.fileuploadplatform.model.FileMetaData;
import com.upload.fileuploadplatform.model.User;

@Repository
public class FileRepository {
	@Autowired
	UserRepository userRepository;

	// Split into two methods - update and add
//	public void uploadFile(FileMetaData fileMetaData, String emailAddress, String fileId) {
//		User user = userRepository.findByEmailAddress(emailAddress);
//		ArrayList<FileMetaData> files = user.getUploadedFiles();
//		if (files != null) {
//			Boolean existingFileUpdate = false;
//			for (int i = 0; i < files.size(); i++) {
//				if (files.get(i).getFileId().equals(fileId)) {
//					existingFileUpdate = true;
//					FileMetaData existingFileMetaData = files.get(i);
//					existingFileMetaData.setDescription(fileMetaData.getDescription());
//					existingFileMetaData.setTitle(fileMetaData.getTitle());
//					files.set(i, existingFileMetaData);
//				}
//			}
//			if (!existingFileUpdate)
//				files.add(fileMetaData);
//
//			user.setUploadedFiles(files);
//		}
//
//		else {
//			ArrayList<FileMetaData> filesList = new ArrayList<>();
//			filesList.add(fileMetaData);
//			user.setUploadedFiles(filesList);
//		}
//		userRepository.save(user);
//	}

	

	public void updateFileMetaData(FileMetaData fileMetaData, String emailAddress, String fileId) {
		User user = userRepository.findByEmailAddress(emailAddress);
		ArrayList<FileMetaData> files = user.getUploadedFiles();
		if(files == null)
			throw  new MetaDataUpdateException("No files have been uploaded yet.Please upload files first and then update metadat");
			
			for (int i = 0; i < files.size(); i++) {
				
				if (files.get(i).getFileId().equals(fileId)) {
					
					FileMetaData existingFileMetaData = files.get(i);
					
					if(fileMetaData.getDescription() != null) {
						existingFileMetaData.setDescription(fileMetaData.getDescription());
					}
					
					
					if(fileMetaData.getTitle()!= null) {
						existingFileMetaData.setTitle(fileMetaData.getTitle());
					}
					
					
					files.set(i, existingFileMetaData);
					break;
				}
			}

			user.setUploadedFiles(files);
		
		
		userRepository.save(user);
		
		return;
	}
	
	public void addFileMetaData(FileMetaData fileMetaData, String emailAddress, String fileId) {
		User user = userRepository.findByEmailAddress(emailAddress);
		ArrayList<FileMetaData> files = user.getUploadedFiles();
		
		if(files == null) {
			files =  new ArrayList<>();
		}
			
			files.add(fileMetaData);

			user.setUploadedFiles(files);
		userRepository.save(user);
	}

	public void removeFileMetaData(String fileId, String emailAddress) {
		User user = userRepository.findByEmailAddress(emailAddress);
		if (user.getUploadedFiles() == null)
			return;
		else {
			ArrayList<FileMetaData> files = user.getUploadedFiles();

			for (int i = 0; i < files.size(); i++) {
				if (files.get(i).getFileId().equals(fileId)) {
					files.remove(i);
				}

			}
			user.setUploadedFiles(files);
			userRepository.save(user);
		}
	}
	


}
