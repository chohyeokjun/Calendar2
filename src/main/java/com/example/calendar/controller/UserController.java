package com.example.calendar.controller;

import com.example.calendar.dto.UserRequestDto;
import com.example.calendar.dto.UserResponseDto;
import com.example.calendar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    // 서비스 접근
    private final UserService userService;

    // 유저 생성
    @PostMapping
    public ResponseEntity<UserResponseDto> saveUser (@RequestBody UserRequestDto requestDto) {
        // 서비스의 저장 메서드 호출
        UserResponseDto userResponseDto = userService.saveUser(requestDto.getUsername(), requestDto.getEmail());
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    // 유저 조회
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findById () {
        List<UserResponseDto> findUsers = userService.findUsers();
        return new ResponseEntity<>(findUsers, HttpStatus.OK);
    }

    // 유저 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser (@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        userService.updateUser(id, requestDto.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
