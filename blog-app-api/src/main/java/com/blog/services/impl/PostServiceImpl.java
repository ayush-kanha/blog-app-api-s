package com.blog.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	//CREATE A POST 
	
	@Override
	public PostDto createPost(PostDto postDto,int userId,int categoryId) {
		
		User user = userRepo.findById(userId).orElseThrow(() -> 
												new ResourceNotFoundException("User", "userID", userId));
		
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> 
					new ResourceNotFoundException("Category", "categoryID", categoryId));
		
		Post post = mapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		//post.setCategory(null);
		post.setCategory(category);
		post.setUser(user);		
		
		Post savedPost = postRepo.save(post);
		
		return mapper.map(savedPost, PostDto.class);
	}

	
	//UPDATE A POST
	
	@Override
	public PostDto updatePost(PostDto dto, int postId) {
		
		//Post post  = mapper.map(dto, Post.class);
		
		Post fetchedPost = postRepo.findById(postId).orElseThrow(() -> 
						new ResourceNotFoundException("Post", "PostId", postId));
		
		//Post post = mapper.map(fetchedPost, Post.class);
		
		fetchedPost.setTitle(dto.getTitle());
		fetchedPost.getCategory().setCategoryId(dto.getCategory().getCategoryId());
		fetchedPost.setContent(dto.getContent());
		fetchedPost.setImageName(dto.getImageName());
		//fetchedPost.setUser(dto.getUser());
		//fetchedPost.set(dto.getCategory().getCategoryId());
		
		postRepo.save(fetchedPost);
		
		return mapper.map(fetchedPost, PostDto.class);
	}
	
	//DELETE A POST

	@Override
	public void deletePost(int postId) {
		
		Post fetchedPost = postRepo.findById(postId).orElseThrow(() -> 
											new ResourceNotFoundException("Post", "PostId", postId));
		
		postRepo.delete(fetchedPost);
	}
	
	//GET A POST BY POSTID

	@Override
	public PostDto getPostByPostId(int postId) {
		
		Post fetchedPost = postRepo.findById(postId).orElseThrow(() -> 
										new ResourceNotFoundException("Post", "PostId", postId));
		
		
		
		return mapper.map(fetchedPost, PostDto.class);
	}
	
	//GET ALL POSTS

	@Override
	public PostResponse getAllPosts(int pageNumber, int pageSize ,String sortBy , String sortDir) {
		
		Sort sort =null;
		
		if(sortDir.equalsIgnoreCase("asc"))
			sort = Sort.by(sortBy).ascending();
		else
			sort = Sort.by(sortBy).descending();
		
		Pageable page = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Post> posts = postRepo.findAll(page);
		
		List<Post> content = posts.getContent();
		
		List<PostDto> dto = content.stream().map(post -> mapper.map(post, PostDto.class)).
																	collect(Collectors.toList());
		
		PostResponse response = new PostResponse();
		
		response.setContent(dto);
		response.setPageNumber(posts.getNumber());
		response.setPageSize(posts.getSize());
		response.setTotalElements(posts.getTotalElements());
		response.setLast(posts.isLast());
		response.setTotalPages(posts.getTotalPages());
		
		
			
		return response;
	}

	//GET POSTS BY CATEGORY
	
	@Override
	public List<PostDto> getPostByCategory(int categoryId) {
		
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> 
								new ResourceNotFoundException("Category", "categoryId", categoryId));
		
								
		List<Post> posts = postRepo.getAllByCategory(category);
		
		List<PostDto> collect = posts.stream().map(post -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return collect;
	}

	//GET POSTS BY USER
	
	@Override
	public List<PostDto> getPostByUser(int userId) {
		
		User user = userRepo.findById(userId).orElseThrow(() -> 
								new ResourceNotFoundException("User", "userId", userId));
		
		List<Post> posts = postRepo.getAllByUser(user);
		
		List<PostDto> collect = posts.stream().map(post -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return collect;
	}
	
	//GET POSTS BY KEYWORD

	@Override
	public List<PostDto> searchPosts(String keyword) {
			
		List<Post> posts = postRepo.findByTitleContaining(keyword);
		
		List<PostDto> searchedPosts = posts.stream().map(post -> mapper.map(post, PostDto.class))
								.collect(Collectors.toList());
		
		return searchedPosts;
	}

}
 