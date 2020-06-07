package com.upload.fileuploadplatform.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.upload.fileuploadplatform.config.CustomUserDetails;
import com.upload.fileuploadplatform.model.User;
import com.upload.fileuploadplatform.repository.UserRepository;

@Service
public class  CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
		User user  = userRepository.findByEmailAddress(emailAddress);
		return new CustomUserDetails(user);
	}

}
