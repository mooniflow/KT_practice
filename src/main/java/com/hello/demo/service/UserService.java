package com.hello.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hello.demo.entity.User;
import com.hello.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 회원가입
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // 로그인 검증 및 세션 저장
    public ResponseEntity<String> login(String username, String password, HttpSession session) {
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
        if (user.isPresent()) {
            session.setAttribute("user", user.get()); // 세션에 사용자 정보 저장
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(401).body("로그인 실패");
        }
    }

    // 로그아웃 (세션 무효화)
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("로그아웃 성공");
    }

    // 모든 사용자 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 사용자 ID로 조회
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // 사용자 삭제
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
