package com.blog.services;

import java.util.List;

import com.blog.entities.Post;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;

public interface PostService {

	//CREATE POST	
	PostDto createPost(PostDto postDto,int userId, int categoryId);
	
	//UPDATE POST	
	PostDto updatePost(PostDto dto,int postId);
	
	//DELETE POST	
	void deletePost(int postId);
	
	//GET SINGLE POST
	PostDto getPostByPostId(int postId);
	
	//GET ALL POSTS
	PostResponse getAllPosts(int pageNumber, int pageSize,String sortBy , String sortDir);
	
	//GET POSTS BY CATEGORY
	List<PostDto> getPostByCategory(int categoryId);
	
	//GET POSTS BY USER
	List<PostDto> getPostByUser(int userId);
	
	//SEARCH BY KEYWORD
	List<PostDto> searchPosts(String keyword);
	
}
