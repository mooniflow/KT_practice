package com.hello.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hello.demo.entity.CodeDetail;

public interface CodeDetailRepository extends JpaRepository<CodeDetail, String> {
    List<CodeDetail> findByGroupId(String groupId);
}
