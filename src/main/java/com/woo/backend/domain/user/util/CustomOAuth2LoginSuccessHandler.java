package com.woo.backend.domain.user.util;

import com.woo.backend.domain.user.entity.User;
import com.woo.backend.global.security.dto.JwtProperties;
import com.woo.backend.global.security.util.JwtTokenProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${front-url}")
    private String frontUrl;

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String token = jwtTokenProvider.generateAccessToken(authentication.getName());

        // Create a cookie with the token
        Cookie cookie = new Cookie("token", token);
//        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(jwtProperties.getAccessTime().intValue() / 1000); // Convert milliseconds to seconds

        response.addCookie(cookie);

        // Redirect to front-end
        getRedirectStrategy().sendRedirect(request, response, frontUrl);

    }
}