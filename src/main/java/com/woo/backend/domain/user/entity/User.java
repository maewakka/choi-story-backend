package com.woo.backend.domain.user.entity;

import com.woo.backend.domain.user.enums.OAuth;
import com.woo.backend.domain.user.enums.Role;
import com.woo.backend.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Table(name = "story_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @Column(unique = true)
    private String userId;

    @Column(unique = true)
    private String nickName;
    private String profileImgPath;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private OAuth oAuth;

    @Builder
    public User(String userId, String nickName, String profileImgPath, Role role, OAuth oAuth) {
        this.userId = userId;
        this.nickName = nickName;
        this.profileImgPath = profileImgPath;
        this.role = role;
        this.oAuth = oAuth;
    }
}
