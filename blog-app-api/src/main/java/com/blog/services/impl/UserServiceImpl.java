package com.blog.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exceptions.DataIntegrityViolationException;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.exceptions.SQLIntegrityConstraintViolationException;
import com.blog.payloads.UserDTO;
import com.blog.repositories.UserRepo;
import com.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDTO createUser(UserDTO userDto){
		
		//converting userdto to user first 
		//through the method below and then passing it to save method...
		try {
			User user = this.userRepo.save(dtoToUser(userDto));
			UserDTO userToDTO = userToDTO(user);
			return userToDTO;
		}
		catch(Exception ex){
			System.out.println("Exception Handled");
			System.out.println(ex.getClass().getName());
			if(ex.getClass().getName().equals("org.springframework.dao.DataIntegrityViolationException"))
				throw new DataIntegrityViolationException("Email "+userDto.getEmail());
			else
				throw new SQLIntegrityConstraintViolationException();
		}
		
		
	}

	@Override
	public UserDTO update(UserDTO userDto, int userId) {
		
		//User user =  new User();
		User user = userRepo.findById(userId).
									orElseThrow(() -> new ResourceNotFoundException("User", "id", userId)
										
									);

			//user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());

		User savedUser = userRepo.save(user);

		return this.userToDTO(savedUser);
	}

	@Override
	public void delete(int userId) {
		
		User user = this.userRepo.findById(userId).
					orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
		//userRepo.deleteById(userId);

	}

	@Override
	public UserDTO getUserById(int userId) {
		
		//UserDTO userDto = new UserDTO();
		User user = userRepo.findById(userId).
					orElseThrow(() -> new ResourceNotFoundException("User", "id", userId)
				);
		
		
  		
		return this.userToDTO(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		
		ArrayList<UserDTO> userDtos = new ArrayList<>();
		List<User> users = userRepo.findAll();
		
		/*
		 * //can user stream too...
		 * 
		 * List<UserDTO> userDtos = users.stream().map(user ->
		 * userToDTO(user)).collect(Collectors.toList());
		 */
		
		for(User user1:users)
			userDtos.add(userToDTO(user1));
		return userDtos;
	}

	
	//for mapping purpose we are using model mapper which will do same work as we map manually 
	//for individual fields
	
	public User dtoToUser(UserDTO userDto)
	{
		User user = modelMapper.map(userDto, User.class);
		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		
		return user;
	}
	
	public UserDTO userToDTO(User user)
	{
		UserDTO userDto = modelMapper.map(user, UserDTO.class);
		
//		userDto.setName(user.getName());
//		userDto.setId(user.getId());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		
		return userDto;
	}
	
	
	
}
