package com.example.demo.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.models.Member;
import com.example.demo.models.Post;
import com.example.demo.repositories.MemberRepository;
import com.example.demo.repositories.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberService {
   @Autowired
   private MemberRepository memberRepository;

   @Autowired
   private PostRepository postRepository;

   public Member findMemberByEmail(String email) {
       return memberRepository.findByEmail(email);
   }

   public List<Member> getAllMembers() {
       return memberRepository.findAll();
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
       final var formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
       System.out.println(String.format("generateData, started at: %s", formatter.format(new Date())));
       final int membersNum = 100;
       final int postsNum = 1000;
       for (int i = 0; i < membersNum; i++) {
           final var member = new Member();
           final var firstName = String.format("firstname%d", i);
           member.setFirstName(firstName);
           member.setLastName("Lastname");
           member.setEmail(String.format("%s@mail.com", firstName));
           memberRepository.save(member);
           for (int j = 0; j < postsNum; j++) {
               final var post = new Post();
               post.setContent(String.format("Post %d by %s", j, firstName));
               post.setMember(member);
               postRepository.save(post);
            }
        }
        System.out.println(String.format("generateData, completed at: %s", formatter.format(new Date())));
    }
}