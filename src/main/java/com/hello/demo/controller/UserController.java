package com.hello.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hello.demo.dto.LoginRequest;
import com.hello.demo.entity.User;
import com.hello.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        return userService.login(loginRequest.getUsername(), loginRequest.getPassword(), session);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        return userService.logout(session);
    }

    // 모든 사용자 조회
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // 특정 사용자 조회
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 사용자 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}