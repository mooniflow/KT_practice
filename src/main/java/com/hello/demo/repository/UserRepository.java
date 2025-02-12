package com.hello.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hello.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
