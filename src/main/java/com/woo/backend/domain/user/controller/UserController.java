package com.woo.backend.domain.user.controller;

import com.woo.backend.domain.user.dto.req.ProfileUpdateReq;
import com.woo.backend.domain.user.dto.resp.UserProfileResp;
import com.woo.backend.domain.user.entity.User;
import com.woo.backend.domain.user.enums.OAuth;
import com.woo.backend.domain.user.facade.UserFacade;
import com.woo.backend.domain.user.service.GetUserService;
import com.woo.backend.global.security.dto.CurrentUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("/profile")
    public UserProfileResp getUserProfile(@CurrentUser User user) {
        return userFacade.getUserProfile(user);
    }

    @PatchMapping("/nickname")
    public ResponseEntity<String> setUserNickName(@CurrentUser User user, @RequestParam(name = "nickName") String nickName) {
        log.info(user.toString());
        userFacade.setUserNickName(user, nickName);

        return ResponseEntity.ok("유저 닉네임이 설정되었습니다.");
    }

    @PatchMapping("/profile")
    public ResponseEntity<String> updateUserProfile(@CurrentUser User user, @RequestBody ProfileUpdateReq req) {
        userFacade.updateUserProfile(user, req);

        return ResponseEntity.ok("유저 프로필이 변경되었습니다.");
    }

    @GetMapping("/nickname")
    public Boolean getExistStatusByNickName(@RequestParam(name = "nickName") String nickname) {
        return userFacade.getExistStatusByNickName(nickname);
    }

}
