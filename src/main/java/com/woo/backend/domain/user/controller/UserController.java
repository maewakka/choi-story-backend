package com.woo.backend.domain.user.controller;

import com.woo.backend.domain.user.dto.resp.UserProfileResp;
import com.woo.backend.domain.user.entity.User;
import com.woo.backend.domain.user.enums.OAuth;
import com.woo.backend.domain.user.service.GetUserService;
import com.woo.backend.global.security.dto.CurrentUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {

    private final GetUserService getUserService;

    @GetMapping("/profile")
    public UserProfileResp getUserProfile(@CurrentUser User user) {
        return getUserService.getUserProfile(user);
    }
}
