package com.woo.backend.domain.user.dto.resp;

import com.woo.backend.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileResp {

    private String nickName;
    private String profileImgPath;

    public static UserProfileResp of(User user, String profileImgPath) {
        return UserProfileResp.builder()
                .nickName(user.getNickName())
                .profileImgPath(profileImgPath)
                .build();
    }

}
