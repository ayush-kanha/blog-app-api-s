package com.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CommentDto;
import com.blog.services.impl.CommentServiceImpl;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	private CommentServiceImpl serviceImpl;
	
	@PostMapping("/post/{postId}/create")
	public ResponseEntity<CommentDto> createComment(@PathVariable int postId,
													@RequestBody CommentDto commentDto)
	{
		CommentDto createdComment = serviceImpl.createComment(commentDto, postId);
		
		return new ResponseEntity<CommentDto>(createdComment,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/delete/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable int commentId)
	{
		serviceImpl.deleteComment(commentId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse(
				"Comment deleted Successfully!!",true),HttpStatus.FOUND);
	}
	
}
