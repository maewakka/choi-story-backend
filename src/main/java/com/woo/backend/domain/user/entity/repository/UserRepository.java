package com.woo.backend.domain.user.entity.repository;

import com.woo.backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsById(String userId);
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.nickName = :nickName")
    boolean existsByNickName(String nickName);
    Optional<User> findUserByUserId(String userId);
}
