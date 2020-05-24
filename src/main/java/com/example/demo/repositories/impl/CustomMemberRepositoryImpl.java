package com.example.demo.repositories.impl;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Member;
import com.example.demo.repositories.CustomMemberRepository;

@Repository
@Transactional(readOnly = true)
public class CustomMemberRepositoryImpl implements CustomMemberRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> findAllMembersWithPosts(Long[] memberIds) {
        return createQuery(memberIds).getResultList();
	}

	private Query createQuery(Long[] memberIds) {
		if (memberIds == null || memberIds.length < 1) {
			return entityManager.createQuery("select distinct m from Member m left outer join fetch m.posts", Member.class);
		}
		return entityManager.createQuery("select distinct m from Member m left outer join fetch m.posts where m.id in :memberIds", Member.class)
				.setParameter("memberIds", Arrays.asList(memberIds));
	}

}
