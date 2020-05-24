package com.example.demo.repositories;

import java.util.List;

import com.example.demo.models.Post;

public interface CustomPostRepository {
	List<Post> findAllPostsWithMembers(Long[] postIdsOptional);
}
