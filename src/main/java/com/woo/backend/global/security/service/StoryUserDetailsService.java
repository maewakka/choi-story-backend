package com.woo.backend.global.security.service;

import com.woo.backend.domain.user.entity.User;
import com.woo.backend.global.security.dto.StoryUserDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoryUserDetailsService implements UserDetailsService {

    private final GetUserWithCachingService getUserWithCachingService;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = getUserWithCachingService.getUserByEmail(email);

        return new StoryUserDetails(null);
    }
}
