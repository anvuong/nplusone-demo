package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Member;

@Repository("memberRepository")
public interface MemberRepository extends JpaRepository<Member, Long>, CustomMemberRepository {
    Member findByEmail(String email);
}