package com.iarlen.reis.portfolioserver.controllers;

import com.iarlen.reis.portfolioserver.DTOs.AuthRequest;
import com.iarlen.reis.portfolioserver.DTOs.AuthResponse;
import com.iarlen.reis.portfolioserver.services.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/portfolio/users/auth")
public class AuthController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<AuthResponse> auth(@RequestBody @Valid AuthRequest data) {
        return loginService.login(data);
    }
}
