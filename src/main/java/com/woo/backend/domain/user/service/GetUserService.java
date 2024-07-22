package com.woo.backend.domain.user.service;

import com.woo.backend.domain.user.dto.resp.UserProfileResp;
import com.woo.backend.domain.user.entity.User;
import com.woo.backend.domain.user.entity.repository.UserRepository;
import com.woo.backend.global.minio.util.MinioUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetUserService {

    private final UserRepository userRepository;
    private final MinioUtil minioUtil;

    @Transactional(readOnly = true)
    public UserProfileResp getUserProfile(User user) {
        log.info(user.toString());
        return UserProfileResp.of(user, minioUtil.getUrlFromMinioObject(user.getProfileImgPath()));
    }
}