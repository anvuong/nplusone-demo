package com.example.demo.repositories.impl;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Post;
import com.example.demo.repositories.CustomPostRepository;

@Repository
@Transactional(readOnly = true)
public class CustomPostRepositoryImpl implements CustomPostRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> findAllPostsWithMembers(Long[] postIds) {
        return createQuery(postIds).getResultList();
	}

	private Query createQuery(Long[] postIds) {
		if (postIds == null || postIds.length < 1) {
			return entityManager.createQuery("select distinct p from Post p inner join fetch p.member", Post.class);
		}
		return entityManager.createQuery("select distinct p from Post p inner join fetch p.member where p.id in :postIds", Post.class)
				.setParameter("postIds", Arrays.asList(postIds));
	}

}
