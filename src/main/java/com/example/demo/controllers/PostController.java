package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Post;
import com.example.demo.models.Views;
import com.example.demo.services.PostService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api/v1")
public class PostController {
  @Autowired
  private PostService postService;

  @GetMapping("/posts")
  @JsonView(Views.Public.class)
  public List<Post> all(@RequestParam("ids") Optional<Long[]> postIdsOptional) {
     return postService.getAllPosts(postIdsOptional.orElse(null));
  }

  @GetMapping("/posts/{id}")
  @JsonView(Views.Public.class)
  public Optional<Post> getPostById(@PathVariable(value= "id") Long id) {
     return postService.getPostById(id);
  }
}