package com.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

	String resourceName;
	String fieldName;
	long fieldvalue;
	
	public ResourceNotFoundException(String resourceName,String fieldName,long fieldValue)
	{
		super(String.format("%s not found for %s : %s", resourceName,fieldName,fieldValue));
		
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldvalue = fieldValue;
		
	}
}
