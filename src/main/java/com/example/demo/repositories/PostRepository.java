package com.example.demo.repositories;

import com.example.demo.models.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("postRepository")
public interface PostRepository 
       extends JpaRepository<Post, Long> {
}