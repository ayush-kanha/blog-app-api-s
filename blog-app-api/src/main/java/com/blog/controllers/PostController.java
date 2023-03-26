package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.repositories.PostRepo;
import com.blog.services.impl.PostServiceImpl;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostServiceImpl postServiceImpl;
	
	//CREATE A POST BY FOR USER AND CATEGORY 
	
	@PostMapping("/user/{userId}/category/{categoryId}/create")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
											  @PathVariable("userId") int userId,
											  @PathVariable("categoryId") int categoryId
										)
	{
		PostDto createdPost = postServiceImpl.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
	}
	
	//UPDATE A POST BY POSTID
	
	@PutMapping("/update/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("postId") int postId)
	{
		PostDto updatedPost = postServiceImpl.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
	}
	
	//GET A POST BY POST ID
	
	@GetMapping("/get/post/{postId}")
	public ResponseEntity<PostDto> getPostByPostId(@PathVariable("postId") int postId)
	{
		PostDto postByPostId = postServiceImpl.getPostByPostId(postId);
		
		return new ResponseEntity<PostDto>(postByPostId,HttpStatus.FOUND);
	}
	
	//GET POSTS BY USER
	
	@GetMapping("/get/post/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("userId") int userId)
	{
		List<PostDto> postByUser = postServiceImpl.getPostByUser(userId);
		
		return new ResponseEntity<>(postByUser,HttpStatus.FOUND);
	}
	
	
	//GET POSTS BY CATEGORY
	
	@GetMapping("/get/post/category/{categoryId}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("categoryId") int categoryId)
	{
		List<PostDto> posts = postServiceImpl.getPostByCategory(categoryId);
		
		return new ResponseEntity<>(posts,HttpStatus.FOUND);
	}
	
	
	//DELETE A POST
	@DeleteMapping("/delete/post/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId)
	{
		postServiceImpl.deletePost(postId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted Successfully", true),
																HttpStatus.FOUND);
	}
	
	//GET ALL POSTS
	
	@GetMapping("/getAll/post")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber",	defaultValue = "0",required = false) int pageNumber,				
			@RequestParam(value = "pageSize",	defaultValue = "5",required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = "asc", required = false) String sortDir
			)
			
	{
		 PostResponse response = postServiceImpl.getAllPosts(pageNumber, pageSize,sortBy,sortDir);
		
		return new ResponseEntity<>(response,HttpStatus.FOUND);
	}
	
	
	//SEARCH POSTS BY KEYWORD
	
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable("keywords") String keyword)
	{
		List<PostDto> result = postServiceImpl.searchPosts(keyword);
		
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	
	
}
