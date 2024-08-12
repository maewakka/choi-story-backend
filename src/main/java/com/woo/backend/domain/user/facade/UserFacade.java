package com.woo.backend.domain.user.facade;

import com.woo.backend.domain.user.dto.req.ProfileUpdateReq;
import com.woo.backend.domain.user.dto.resp.UserProfileResp;
import com.woo.backend.domain.user.entity.User;
import com.woo.backend.domain.user.service.GetUserService;
import com.woo.backend.domain.user.service.UpdateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final GetUserService getUserService;
    private final UpdateUserService updateUserService;

    public UserProfileResp getUserProfile(User user) {
        return getUserService.getUserProfile(user);
    }

    @Transactional
    public void setUserNickName(User user, String nickName) {
        updateUserService.setNickName(user, nickName);
    }

    @Transactional
    public void updateUserProfile(User user, ProfileUpdateReq req) {
        updateUserService.updateUserProfile(user, req);
    }

    @Transactional(readOnly = true)
    public Boolean getExistStatusByNickName(String nickName) {
        return getUserService.getExistStatusByNickName(nickName);
    }
}
