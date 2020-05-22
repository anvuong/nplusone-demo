package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Post;
import com.example.demo.services.PostService;

@RestController
@RequestMapping("/api/v1")
public class PostController {
  @Autowired
  private PostService postService;

  @GetMapping("/posts")
  public List<Post> all() {
     return postService.getAllPosts();
  }
  
  @GetMapping("/posts/{id}")
  public Optional<Post> getPostById(@PathVariable(value= "id") Long id) {
     return postService.getPostById(id);
  }
}