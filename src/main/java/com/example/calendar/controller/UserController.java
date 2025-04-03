package com.example.calendar.controller;

import com.example.calendar.common.Const;
import com.example.calendar.dto.*;
import com.example.calendar.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    // 서비스 접근
    private final UserService userService;

    // 유저 생성 (회원 가입)
    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponseDto> signUp (@RequestBody UserRequestDto requestDto) {
        // 서비스의 저장 메서드 호출
        UserSignUpResponseDto userSignUpResponseDto = userService.signUp(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());
        return new ResponseEntity<>(userSignUpResponseDto, HttpStatus.CREATED);
    }

    // 유저 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById (@PathVariable Long id) {
        UserResponseDto findUsers = userService.findUsers(id);
        return new ResponseEntity<>(findUsers, HttpStatus.OK);
    }

    // 유저 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser (@PathVariable Long id, @RequestBody UserEmailRequestDto emailRequestDto) {
        userService.updateUser(id, emailRequestDto.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login (@RequestBody LoginRequestDto requestDto, HttpServletRequest request) {
        // 로그인 메서드 호출
        LoginResponseDto loginResponseDto = userService.login(requestDto.getEmail(), requestDto.getPassword());
        // userId 변수에 저장
        Long userId = loginResponseDto.getId();
        // 실패시 예외처리
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        // 로그인 성공시 로직
        // default 값은 true
        HttpSession session = request.getSession();
        // 회원 정보 조회
        UserResponseDto loginUser = userService.findUsers(userId);
        // session에 로그인 회원 정보 저장
        session.setAttribute(Const.LOGIN_USER, loginUser);
        // 로그인 성공
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 로그아웃
    @PostMapping("/logout")
    public String logout (HttpServletRequest request) {
        // 로그인이 아닐 시 HttpSession이 null 값 반환
        HttpSession session = request.getSession(false);
        // 세션이 있으면 로그인이 된 경우
        if (session != null) {
            session.invalidate(); // 해당 세션 삭제
        }
        return "로그아웃";
    }
}
