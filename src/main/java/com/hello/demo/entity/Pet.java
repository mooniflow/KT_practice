package com.hello.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private int age;
    private String photo;

    // 기본 생성자
    public Pet() {}

    // 매개변수가 있는 생성자
    public Pet(String name, String type, int age, String photo) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.photo = photo;
    }
}
