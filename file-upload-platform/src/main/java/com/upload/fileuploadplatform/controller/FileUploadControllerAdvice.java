package com.upload.fileuploadplatform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.upload.fileuploadplatform.exception.MetaDataUpdateException;

@ControllerAdvice
public class FileUploadControllerAdvice {
	
	@ExceptionHandler(value = {MetaDataUpdateException.class})
	public ResponseEntity<Object> handleMetadataUpdateException(MetaDataUpdateException e) {
		
		return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
