package com.upload.fileuploadplatform.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.upload.fileuploadplatform.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	public User   findByEmailAddress(String emailAddress);
	
	
}
