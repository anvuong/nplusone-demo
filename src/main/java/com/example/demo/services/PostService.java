package com.example.demo.services;

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

   public List<Post> getAllPosts() {
       return postRepository.findAll();
   }

   public Optional<Post> getPostById(Long id) {
       return postRepository.findById(id);
   }
}