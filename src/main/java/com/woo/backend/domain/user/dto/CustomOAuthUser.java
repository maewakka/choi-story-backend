package com.woo.backend.domain.user.dto;

import com.woo.backend.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.jar.Attributes;

@Builder
@Getter
public class CustomOAuthUser implements OAuth2User {

    private Map<String, Object> attributes;
    private User user;

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(user.getRole().getValue()));
        return auth;
    }

    @Override
    public String getName() {
        return user.getUserId();
    }
}
