package com.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.validation.UnexpectedTypeException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
	{
		String message = ex.getMessage();
		return new ResponseEntity<>(new ApiResponse(message,false),HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsAreNotValidException(
												MethodArgumentNotValidException ex)
	{
		Map<String,String> resp = new HashMap<>();
		
		
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			
				String field = ((FieldError)error).getField();
				String message = error.getDefaultMessage();
				
				resp.put(field, message);		
			
						}			
				);
		
		return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ApiResponse> handleDataIntegrityViolationException(
															SQLIntegrityConstraintViolationException ex)
	{
		String message = ex.getMessage();
		
		return new ResponseEntity<ApiResponse>(new ApiResponse(message,false),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiResponse> handleDataIntegrityViolationExceptionn(
																	DataIntegrityViolationException ex)
	{
		String message = ex.getMessage();
		
		return new ResponseEntity<ApiResponse>(new ApiResponse(message,false),HttpStatus.BAD_REQUEST);
	}
	
	
	/*
	 * @ExceptionHandler(UnexpectedTypeException.class) public
	 * ResponseEntity<Map<String,String>>
	 * handleMethodArgsDyuplicateException(UnexpectedTypeException ex) { Map<String,
	 * String> resp = new HashMap<>();
	 * 
	 * 
	 * 
	 * return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST); }
	 */
	
	
	
	
	
	
}
