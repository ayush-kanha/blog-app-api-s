package com.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> getAllByUser(User user);
	List<Post> getAllByCategory(Category category);
	
	List<Post> findByTitleContaining(String keyword);
}
