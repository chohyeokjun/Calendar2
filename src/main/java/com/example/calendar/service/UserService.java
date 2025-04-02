package com.example.calendar.service;

import com.example.calendar.dto.UserResponseDto;
import com.example.calendar.entity.User;
import com.example.calendar.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    // repository 접근
    private final UserRepository userRepository;

    public UserResponseDto saveUser(String username, String email) {
        // 파라미터 값을 넘겨준 user 객체 생성
        User user = new User(username, email);
        // 위 user 객체를 DB에 저장한 savedUser 객체 생성
        User savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail(), savedUser.getCreatedAt(), savedUser.getModifiedAT());
    }

    public List<UserResponseDto> findUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getCreatedAt(),
                        user.getModifiedAT()
                ))
                .toList();
    }

    @Transactional
    public void updateUser(Long id, String email) {
        // 유저 정보 조회
        User findUser = userRepository.findByIdOrElseThrow(id);
        // 해당 유저 정보 수정
        findUser.updateUser(email);
    }

    public void deleteUser(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        userRepository.delete(findUser);

    }
}
