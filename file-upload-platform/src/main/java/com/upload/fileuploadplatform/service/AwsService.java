package com.upload.fileuploadplatform.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.upload.fileuploadplatform.model.FileMetaData;
import com.upload.fileuploadplatform.repository.FileRepository;
import com.upload.fileuploadplatform.repository.UserRepository;


@Service
public class AwsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AwsService.class);
	
	@Autowired
	AmazonS3 s3Client;
	
	@Autowired
	FileRepository fileRepository;
	
	public String uploadFile(final MultipartFile multipartFile, String emailAddress) {
        LOGGER.info("File upload in progress.");
        String fileId=null;
        try {
            final File file = convertMultiPartFileToFile(multipartFile);
             fileId = uploadFileToS3Bucket("file-upload-platform", file, emailAddress);
            LOGGER.info("File upload is completed.");
            file.delete();  // To remove the file locally created in the project folder.
        } catch (final AmazonServiceException ex) {
            LOGGER.info("File upload is failed.");
            LOGGER.error("Error= {} while uploading file.", ex.getMessage());
        }
        return fileId;
    }
	
	public void removeFile(String fileId, String emailAddress) {
        LOGGER.info("File remove in progress.");
        
        fileRepository.removeFile(fileId, emailAddress);
        
        try {
        	DeleteObjectRequest  deleteObjectRequest= new DeleteObjectRequest("file-upload-platform", fileId);
        	s3Client.deleteObject(deleteObjectRequest);
     
            LOGGER.info("File delete is completed.");
        } catch (final AmazonServiceException ex) {
            LOGGER.info("File delete is failed.");
            LOGGER.error("Error= {} while uploading file.", ex.getMessage());
        }
    }
	
	private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            LOGGER.error("Error converting the multi-part file to file= ", ex.getMessage());
        }
        return file;
    }
	
	private String uploadFileToS3Bucket(final String bucketName, final File file, String emailAddress) {
		
		FileMetaData fileMetaData = new FileMetaData();
				
		
		String fileId = UUID.randomUUID().toString();
		String fileName = file.getName();
		String uploadedTimeStamp = LocalDateTime.now().toString();
        
		fileMetaData.setFileId(fileId);
        fileMetaData.setUploadedTimeStamp(uploadedTimeStamp);
        fileMetaData.setFileName(fileName);
        
        fileRepository.uploadFile(fileMetaData, emailAddress, fileId);
        
        LOGGER.info("Uploading file  with file ID" + fileId);
        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileId, file);
        s3Client.putObject(putObjectRequest);
        return fileId;
    }

}
