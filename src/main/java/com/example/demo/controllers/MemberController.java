package com.example.demo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Member;
import com.example.demo.services.MemberService;

@RestController
@RequestMapping("/api/v1")
public class MemberController {
  @Autowired
  private MemberService memberService;

  @GetMapping("/members")
  public List<Member> all(@RequestParam("ids") Optional<Long[]> memberIdsOptional) {
     return memberService.getAllMembers(memberIdsOptional.orElse(null));
  }

  @GetMapping("/members/{id}")
  public Optional<Member> getMemberById(@PathVariable(value= "id") Long id) {
	  final var startedTime = new Date();
	  final var member = memberService.getMemberById(id);
	  final var endedTime = new Date();
	  final var dateFormatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
	  System.out.println(String.format(
			  "getMemberById with id: %d; started at: %s, ended at: %s, time taken: %.2f seconds",
			  id,
			  dateFormatter.format(startedTime),
			  dateFormatter.format(endedTime),
			  (endedTime.getTime() - startedTime.getTime()) / 1000.0
			  ));
	  return member;
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