package com.blog.payloads;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private int categoryId;
	
	@NotBlank
	@Size(min = 4,message = "title must not be empty also it should have minimum 4 characters.")
	@Column(unique = true)
	private String categoryTitle;	

	@NotBlank
	@Size(min = 10,message = "Description must not be empty also it should have minimum 4 characters")
	private String categoryDescription;

}
