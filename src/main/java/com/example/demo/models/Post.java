package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name= "posts")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    @JsonView(Views.Public.class)
    private Long id;

    @Column(name="content")
    @NotEmpty(message="* Please Enter Content")
    @JsonView(Views.Public.class)
    private String content;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name="member_id", nullable=false)
    @JsonView(Views.Public.class)
    private Member member;
}