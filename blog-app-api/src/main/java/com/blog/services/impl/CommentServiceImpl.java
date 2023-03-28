package com.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CommentDto;
import com.blog.payloads.PostDto;
import com.blog.repositories.CommentRepo;
import com.blog.repositories.PostRepo;
import com.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, int postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(() ->
										new ResourceNotFoundException("Post", "postId", postId));
		
		Comment comment = this.mapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment savedComent = commentRepo.save(comment);
				
		return this.mapper.map(savedComent, CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {
		
		Comment comment = commentRepo.findById(commentId).orElseThrow(() ->
									new ResourceNotFoundException("Comment", "commentId", commentId));
		
		commentRepo.delete(comment);
		
	}

}
