package com.iarlen.reis.portfolioserver.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.iarlen.reis.portfolioserver.models.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserModel user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);

            return JWT.create()
                    .withIssuer("portfolio-server")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId().toString())
                    .withExpiresAt(expireDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error ao gerar token JWT", exception);
        }
    }

    public Instant expireDate() {
        return LocalDateTime.now()
                .plusHours(24)
                .toInstant(ZoneOffset.of("-03:00"));
    }

    public String verifyToken (String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("portfolio-server")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception exception) {
            throw new RuntimeException("Token JWT inválido/expirado", exception);
        }
    }
}
