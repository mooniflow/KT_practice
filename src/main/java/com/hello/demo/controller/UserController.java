package com.hello.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hello.demo.dto.LoginRequest;
import com.hello.demo.entity.User;
import com.hello.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:8082") // CORS 설정 추가
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
    public ResponseEntity<Long> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
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

    // 회원 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    // 세션 확인
    @GetMapping("/session")
    public ResponseEntity<User> checkSession(HttpSession session) {
        return userService.checkSession(session);
    }

    // 닉네임 수정
    @PutMapping("/{id}/nickname")
    public ResponseEntity<User> updateNickname(@PathVariable("id") Long id, @RequestBody String newNickname) {
        return userService.updateNickname(id, newNickname);
    }

    // 비밀번호 변경
    @PutMapping("/{id}/password")
    public ResponseEntity<User> changePassword(@PathVariable("id") Long id, @RequestBody String newPassword) {
        return userService.changePassword(id, newPassword);
    }
}