package com.woo.backend.domain.user.entity.repository;

import com.woo.backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUserId(String userId);
    Optional<User> findUserByUserId(String userId);
}
