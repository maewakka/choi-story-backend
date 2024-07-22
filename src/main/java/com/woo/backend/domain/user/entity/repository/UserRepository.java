package com.woo.backend.domain.user.entity.repository;

import com.woo.backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsById(String id);
}
