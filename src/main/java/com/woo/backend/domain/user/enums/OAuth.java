package com.woo.backend.domain.user.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum OAuth {

    KAKAO("카카오", "kakao"), GOOGLE("구글", "google"), NAVER("naver", "naver");

    String name;
    String value;

    OAuth(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static OAuth fromValue(String value) {
        for (OAuth oAuth : OAuth.values()) {
            if (oAuth.getValue().equalsIgnoreCase(value)) {
                return oAuth;
            }
        }
        return null;
    }
}
