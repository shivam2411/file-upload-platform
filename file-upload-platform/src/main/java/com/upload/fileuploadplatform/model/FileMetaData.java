package com.upload.fileuploadplatform.model;

public class FileMetaData {
	private String fileId;
	private String fileName;
	private String uploadedTimeStamp;
	
	private String description;
	
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUploadedTimeStamp() {
		return uploadedTimeStamp;
	}

	public void setUploadedTimeStamp(String uploadedTimeStamp) {
		this.uploadedTimeStamp = uploadedTimeStamp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
