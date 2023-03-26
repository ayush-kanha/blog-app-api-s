package com.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entities.User;
import com.blog.payloads.ApiResponse;
import com.blog.payloads.UserDTO;
import com.blog.repositories.UserRepo;
import com.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController 
{
	@Autowired
	private UserService userService;
	
	//CREATE A USER
	
	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto)
	{
		UserDTO createdUserDTO = userService.createUser(userDto);
		return new ResponseEntity<>(createdUserDTO,HttpStatus.CREATED);
	}
	
	//GET A USER BY USER_ID
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUserByUserId(@PathVariable("userId") int userId)
	{
		UserDTO userDto = userService.getUserById(userId);
		
		return new ResponseEntity<>(userDto,HttpStatus.FOUND);
	}
	
	
	//GET ALL USERS
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserDTO>> getAllUsers()
	{
		List<UserDTO> allUsers = userService.getAllUsers();
		return new ResponseEntity<>(allUsers,HttpStatus.FOUND);
	}
	
	//UPDATE A USER
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDto,@PathVariable("userId") int userId)
	{
		UserDTO updatedUser = userService.update(userDto, userId);
		return new ResponseEntity<>(updatedUser,HttpStatus.OK);
	}
	
	//DELETE A USER
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") int userId)
	{
		userService.delete(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully.",true),HttpStatus.OK);
	}
	
	
	
}
