package com.woo.backend.domain.user.service;

import com.woo.backend.domain.user.dto.resp.UserProfileResp;
import com.woo.backend.domain.user.entity.User;
import com.woo.backend.domain.user.entity.repository.UserRepository;
import com.woo.backend.global.minio.util.MinioUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetUserService {

    private final UserRepository userRepository;
    private final MinioUtil minioUtil;

    public UserProfileResp getUserProfile(User user) {
        return UserProfileResp.of(user, minioUtil.getUrlFromMinioObject(user.getProfileImgPath()));
    }

    public Boolean getExistStatusByNickName(String nickName) {
        if (nickName.length() < 3 || nickName.contains(" ")) {
            return true;
        }
        return userRepository.existsByNickName(nickName);
    }
}