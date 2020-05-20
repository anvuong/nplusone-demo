package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.demo.models.Member;
import com.example.demo.services.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MemberController {
  @Autowired
  private MemberService memberService;

  @GetMapping("/members")
  public List<Member> all() {
     return memberService.getAllMembers();
  }

  @PostMapping("/members")
  public ResponseEntity<Member> createMember (@Valid @RequestBody Member member) {
      return ResponseEntity.ok(memberService.saveMember(member));
  }

  @PostMapping("/members/generate")
  public String generateMembers() {
      memberService.generateData();
      return "Members are being generated";
  }

  @PutMapping("/members/{id}")
  public ResponseEntity<Member> updateMember(@Valid @RequestBody Member member,
    @PathVariable(value= "id") Long id) {
        return ResponseEntity.ok(memberService.updateMember(member, id));
    }

  @DeleteMapping("/members/{id}")
  public ResponseEntity<?> deleteMemeber(@PathVariable Long id) {
    Map<String,String> response = new HashMap<String,String>();
    if(memberService.deleteMember(id)) {
      response.put("status", "success");
      response.put("message", "member deleted successfully");
       return ResponseEntity.ok(response);
    } else {
      response.put("status", "error");
      response.put("message", "Somthing went wrong when delete the member");
      return ResponseEntity.status(500).body(response);
    }
  }
}