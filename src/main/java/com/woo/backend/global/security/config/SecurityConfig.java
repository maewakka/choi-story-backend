package com.woo.backend.global.security.config;

import com.woo.backend.domain.user.util.CustomOAuth2LoginFailureHandler;
import com.woo.backend.domain.user.util.CustomOAuth2LoginSuccessHandler;
import com.woo.backend.global.security.filter.JwtFilter;
import com.woo.backend.global.security.service.StoryUserDetailsService;
import com.woo.backend.global.security.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2LoginSuccessHandler successHandler;
    private final CustomOAuth2LoginFailureHandler failureHandler;
    private final JwtTokenProvider jwtTokenProvider;
    private final StoryUserDetailsService storyUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .successHandler(successHandler)
                                .failureHandler(failureHandler)
                )
                .addFilterBefore(new JwtFilter(storyUserDetailsService, jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}