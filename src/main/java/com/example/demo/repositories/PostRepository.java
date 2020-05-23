package com.example.demo.repositories;

import com.example.demo.models.Post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("postRepository")
public interface PostRepository 
       extends JpaRepository<Post, Long> {

	@Query("select distinct p from Post p inner join fetch p.member")
	List<Post> findAll();

	@Query("select distinct p from Post p inner join fetch p.member where p.id in :postIds")
	List<Post> findAllById(@Param("postIds") List<Long> postIds);
}