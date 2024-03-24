package com.iarlen.reis.portfolioserver.services;

import com.iarlen.reis.portfolioserver.DTOs.AuthRequest;
import com.iarlen.reis.portfolioserver.DTOs.AuthResponse;
import com.iarlen.reis.portfolioserver.models.UserModel;
import com.iarlen.reis.portfolioserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public ResponseEntity<AuthResponse> login(AuthRequest data) {
        UserModel user = userRepository.findByEmail(data.email());

        if(user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("E-mail ou Senha inválidos."));
        }

        if(!passwordEncoder.matches(data.password(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("E-mail ou Senha inválidos."));
        }

        var token = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = authenticationManager.authenticate(token);

        var generatedToken = tokenService.generateToken((UserModel) authentication.getPrincipal());

        return ResponseEntity.ok(new AuthResponse(generatedToken));
    }
}
