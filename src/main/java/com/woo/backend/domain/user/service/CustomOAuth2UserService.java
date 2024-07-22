package com.woo.backend.domain.user.service;

import com.woo.backend.domain.user.dto.CustomOAuthUser;
import com.woo.backend.domain.user.dto.OAuthAttributes;
import com.woo.backend.domain.user.entity.User;
import com.woo.backend.domain.user.entity.repository.UserRepository;
import com.woo.exception.util.BizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 로그인 진행 중인 서비스를 구분
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // OAuth Attribute
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        // 사용자 저장
        User user = saveUser(attributes);

        return CustomOAuthUser.builder()
                .user(user)
                .attributes(attributes.getAttributes())
                .build();
    }

    private User saveUser(OAuthAttributes attributes) {
        if(userRepository.existsByUserId(attributes.getId())) return userRepository.findById(attributes.getId()).orElseThrow(() -> new BizException("user_not_found"));

        return userRepository.save(attributes.toEntity());
    }
}
