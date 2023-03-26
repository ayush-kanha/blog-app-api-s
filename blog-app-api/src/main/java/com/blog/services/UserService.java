package com.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.payloads.UserDTO;

public interface UserService {

	UserDTO createUser(UserDTO user) ;	
	
	UserDTO update(UserDTO user, int userId);
	
	void delete(int userId);
	
	UserDTO getUserById(int userId);
	
	List<UserDTO> getAllUsers();
}
