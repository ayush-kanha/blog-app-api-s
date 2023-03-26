package com.blog.exceptions;

public class SQLIntegrityConstraintViolationException extends RuntimeException{
	
	public SQLIntegrityConstraintViolationException()
	{
		super("The values are viaolating some sql contraints please check...");
		
	}
	
}
