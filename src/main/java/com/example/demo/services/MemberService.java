package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.models.Member;
import com.example.demo.models.Post;
import com.example.demo.repositories.MemberRepository;

@Service("memberService")
public class MemberService {
   @Autowired
   private MemberRepository memberRepository;

   @PersistenceContext
   private EntityManager entityManager;

   @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
   private int batchSize;

   public Member findMemberByEmail(String email) {
       return memberRepository.findByEmail(email);
   }

   public List<Member> getAllMembers() {
       return memberRepository.findAll();
   }

   public Optional<Member> getMemberById(Long id) {
       return memberRepository.findById(id);
   }

   public Member saveMember(Member member) {
       return memberRepository.save(member);
   }

   public Member updateMember(Member member, Long id) {
     Member updateMember = memberRepository.findById(id).orElse(null);
     if(updateMember != null) {
         updateMember.setFirstName(member.getFirstName());
         updateMember.setLastName(member.getLastName());
     }
     final Member mymember = memberRepository.save(updateMember);
     return mymember;
   }

   public Boolean deleteMember (Long id) {
      Member delMember  = memberRepository.findById(id).orElse(null);
      if(delMember != null) {
          memberRepository.delete(delMember);
          return true;
      }
      return false;
   }

   @Transactional
   @Async
   public void generateData() {
       final int membersNum = 100;
       final int postsNum = 1000;
       for (int i = 0; i < membersNum; i++) {
           final var member = new Member();
           final var firstName = String.format("firstname%d", i);
           member.setFirstName(firstName);
           member.setLastName("Lastname");
           member.setEmail(String.format("%s@mail.com", firstName));
           entityManager.persist(member);
           for (int j = 0; j < postsNum; j++) {
               final var post = new Post();
               post.setContent(String.format("Post %d by %s", j, firstName));
               post.setMember(member);
               entityManager.persist(post);
           }
           if (i > 0 && i % batchSize == 0) {
               entityManager.flush();
               entityManager.clear();
           }
        }
    }
}