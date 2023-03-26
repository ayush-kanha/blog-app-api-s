package com.blog.payloads;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

	private int id;
	
	@NotEmpty
	@Size(min = 4,max = 50,message = "User name should be minimum 4 characters and maximum 50 charracters.")
	private String name;
	
	@Email(message = "email is not valid")
	@NotEmpty
	@Pattern(regexp = "[A-Za-z0-9_]*@[a-z]*\\.[a-z]{2,3}")
	//@Column(unique = true),columnDefinition =  "Email already exists ,please provide new email.")
	private String email;
	
	@NotEmpty
	@Size(min = 5, max = 10,message = "password must be minimum 5 chars and maximum of 10 chars.")
	@Pattern(regexp = "[A-Za-z0-9]{5,10}[@]+[#]+[A-Za-z]+")
	private String password;
	
	@NotEmpty
	private String about; 
	
}
