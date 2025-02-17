package com.hello.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hello.demo.entity.CodeGroup;

public interface CodeGroupRepository extends JpaRepository<CodeGroup, String> {
}
