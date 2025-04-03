package com.example.calendar.service;

import com.example.calendar.dto.LoginResponseDto;
import com.example.calendar.dto.UserResponseDto;
import com.example.calendar.entity.User;
import com.example.calendar.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    // repository 접근
    private final UserRepository userRepository;

    // 회원가입
    public UserResponseDto signUp(String username, String email, String password) {
        // 파라미터 값을 넘겨준 user 객체 생성
        User user = new User(username, email, password);
        // 위 user 객체를 DB에 저장한 savedUser 객체 생성
        User signUp = userRepository.save(user);
        return new UserResponseDto(signUp.getId(), signUp.getUsername(), signUp.getEmail(), signUp.getCreatedAt(), signUp.getModifiedAT());
    }

    // 유저 조회
    public UserResponseDto findUsers(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        return new UserResponseDto(id, findUser.getUsername(), findUser.getEmail(), findUser.getCreatedAt(), findUser.getModifiedAT());
    }

    // 수정
    @Transactional
    public void updateUser(Long id, String email, String password) {
        // 유저 정보 조회
        User findUser = userRepository.findByIdOrElseThrow(id);
        // 해당 유저 정보 수정
        findUser.updateUser(email);
    }

    // 유저 삭제 필요??
    public void deleteUser(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        userRepository.delete(findUser);

    }

    // 로그인
    public LoginResponseDto login (String email, String password) {
        // 유저 조회
        User findUser = userRepository.findUserByEmailOrElseThrow(email);
        // 비밀번호 검증
        if (!findUser.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        return new LoginResponseDto(findUser.getId(), findUser.getUsername(), findUser.getEmail());
    }
}
