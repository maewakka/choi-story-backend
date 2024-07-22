package com.woo.backend.domain.user.enums;

import lombok.Getter;

@Getter
public enum Role {

    USER("사용자", "ROLE_USER"), ADMIN("관리자", "ROLE_ADMIN");

    private final String name;
    private final String value;

    Role(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
