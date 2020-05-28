package com.upload.fileuploadplatform.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.upload.fileuploadplatform.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	public User   findByName(String name);
	
	
}
