package com.iarlen.reis.portfolioserver.services;

import com.iarlen.reis.portfolioserver.DTOs.CreateUserRequest;
import com.iarlen.reis.portfolioserver.DTOs.CreateUserResponse;
import com.iarlen.reis.portfolioserver.models.UserModel;
import com.iarlen.reis.portfolioserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseEntity<CreateUserResponse> createUser(CreateUserRequest data) {
        UserModel user = new UserModel(data.name(), data.email().toLowerCase(), passwordEncoder.encode(data.password()));

        String encodedPassword = passwordEncoder.encode(data.password());
        user.setPassword(encodedPassword);

        this.userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateUserResponse("Usuário criado com sucesso."));
    }
}
