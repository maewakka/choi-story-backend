package com.woo.backend.domain.user.dto;

import com.woo.backend.domain.user.entity.User;
import com.woo.backend.domain.user.enums.OAuth;
import com.woo.backend.domain.user.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Getter
@Setter
@ToString
@Slf4j
public class OAuthAttributes {

    private String id;
    private Map<String, Object> attributes;
    private String attributeName;
    private String registrationId;

    @Builder
    public OAuthAttributes(String id, Map<String, Object> attributes, String attributeName, String registrationId) {
        this.id = id;
        this.attributes = attributes;
        this.attributeName = attributeName;
        this.registrationId = registrationId;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        OAuthAttributes oAuthAttributes = null;

        switch (registrationId) {
            case "google" -> oAuthAttributes = ofGoogle(userNameAttributeName, attributes);
            case "kakao" -> oAuthAttributes = ofKakao(userNameAttributeName, attributes);
            case "naver" -> oAuthAttributes = ofNaver(userNameAttributeName, attributes);
            default ->  {return null;}
        }

        oAuthAttributes.setRegistrationId(registrationId);
        return oAuthAttributes;
    }

    private static OAuthAttributes ofGoogle(String usernameAttributeName,
                                            Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .attributes(attributes)
                .attributeName(usernameAttributeName)
                .id(attributes.get(usernameAttributeName).toString())
                .build();
    }

    private static OAuthAttributes ofKakao(String usernameAttributeName,
                                            Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .attributes(attributes)
                .attributeName(usernameAttributeName)
                .id(attributes.get(usernameAttributeName).toString())
                .build();
    }

    private static OAuthAttributes ofNaver(String usernameAttributeName,
                                           Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get(usernameAttributeName);

        return OAuthAttributes.builder()
                .attributes(attributes)
                .attributeName(usernameAttributeName)
                .id(response.get("id").toString())
                .build();
    }

    // User 엔티티 생성
    public User toEntity() {
        return User.builder()
                .userId(id)
                .role(Role.USER)
                .oAuth(OAuth.fromValue(registrationId))
                .profileImgPath("profile_img/default_profile.png")
                .build();
    }
}
