package com.woo.backend.domain.user.controller;

import com.woo.backend.domain.user.enums.OAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {


    @GetMapping("/login/{oauth}")
    public ResponseEntity<?> loginWithOAuth(@PathVariable(name = "oauth") OAuth oAuth) {
        log.info(oAuth.toString());

        String googleOAuthUrl = "http://localhost:8080/oauth2/authorization/google";
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(googleOAuthUrl));
        return ResponseEntity.status(302).headers(headers).build();

//        return ResponseEntity.status(302)
//                .header("Location", "")
//                .build();
    }
}
