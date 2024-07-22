package com.woo.backend.global.security.filter;

import com.woo.backend.global.security.service.StoryUserDetailsService;
import com.woo.backend.global.security.util.JwtTokenProvider;
import com.woo.exception.util.BizException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final StoryUserDetailsService storyUserDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    private final static List<String> PERMIT_URL = List.of("/users/sign-up", "/users/sign-in", "/users/code/send", "/users/code/verify");
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!PERMIT_URL.contains(request.getServletPath())) {
            String accessToken = jwtTokenProvider.resolveToken(request);

            if(accessToken == null || accessToken.isEmpty()) throw new BizException("token_not_valid");
            setAuthentication(accessToken, request);
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(String token, HttpServletRequest request) throws ExpiredJwtException {
        String id = jwtTokenProvider.extractAllClaims(token).get("id", String.class);
        UserDetails userDetails = storyUserDetailsService.loadUserByUsername(id);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
