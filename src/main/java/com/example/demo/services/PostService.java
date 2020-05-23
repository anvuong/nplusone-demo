package com.example.demo.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Post;
import com.example.demo.repositories.PostRepository;

@Service("postService")
public class PostService {
   @Autowired
   private PostRepository postRepository;

   public List<Post> getAllPosts(Long[] postIds) {
	   if (postIds == null || postIds.length < 1) {
		   return postRepository.findAll();
	   }
       return postRepository.findAllById(Arrays.asList(postIds));
   }

   public Optional<Post> getPostById(Long id) {
       return postRepository.findById(id);
   }
}