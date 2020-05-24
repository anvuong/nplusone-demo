package com.example.demo.repositories;

import java.util.List;

import com.example.demo.models.Member;

public interface CustomMemberRepository {
	List<Member> findAllMembersWithPosts(Long[] memberIdsOptional);
}
