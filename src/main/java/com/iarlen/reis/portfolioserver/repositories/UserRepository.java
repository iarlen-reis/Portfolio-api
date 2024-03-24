package com.iarlen.reis.portfolioserver.repositories;

import com.iarlen.reis.portfolioserver.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

    UserModel findByEmail(String email);
}
