package com.example.demo.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name= "members")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Member {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="member_id")
    @JsonView(Views.Public.class)
    private Long id;

    @Column(name="first_name")
    @NotEmpty(message="* Please Enter First Name")
    @JsonView(Views.Public.class)
    private String firstName;

    @Column(name="last_name")
    @NotEmpty(message="* Please Enter Last Name")
    @JsonView(Views.Public.class)
    private String lastName;

    @Email(message="* Please Enter Valid Email Address")
    @NotEmpty(message=" * Please Provide Email Address")
    @Column(name="email")
    @JsonView(Views.Public.class)
    private String email;

    @OneToMany(mappedBy="member")
    @OrderBy("id")
    @JsonView(Views.Internal.class)
    private Set<Post> posts;
}