package com.iarlen.reis.portfolioserver.DTOs;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
        @NotBlank
        String name,

        @NotBlank
        String email,
        @NotBlank
        String password
) {}
