package com.blog.exceptions;

public class DataIntegrityViolationException extends RuntimeException{

	private String value;
	
	public DataIntegrityViolationException(String value)
	{
		super(String.format("%s is already registered please use other one",value));
		
		value = this.value;
	}
	
}
