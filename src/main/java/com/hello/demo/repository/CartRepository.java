package com.hello.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hello.demo.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
