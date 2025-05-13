package me.heojaeyeon.board.springbootdeveloper.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.heojaeyeon.board.springbootdeveloper.DTO.AddUserRequest;
import me.heojaeyeon.board.springbootdeveloper.domain.User;
import me.heojaeyeon.board.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;


    public Long save(AddUserRequest dto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                //패스워드 암호화
                .password(encoder.encode(dto.getPassword()))
                .build()).getId();
    }

    //전달받은 유저 ID로 유저를 검색해서 토큰을 전달하는 메서드 추가
    public User findById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Unexpected user"));
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

}
