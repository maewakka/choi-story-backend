package com.woo.backend.domain.user.service;

import com.woo.backend.domain.user.dto.req.ProfileUpdateReq;
import com.woo.backend.domain.user.entity.User;
import com.woo.backend.domain.user.entity.repository.UserRepository;
import com.woo.backend.global.minio.util.MinioUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserService {

    private final UserRepository userRepository;
    private final MinioUtil minioUtil;

    public void setNickName(User user, String nickName) {
        user.updateNickName(nickName);

        userRepository.save(user);
    }

    public void updateUserProfile(User user, ProfileUpdateReq req) {
        if(req.getProfileImg() != null) {
            String profileImgUrl = "profile_img/" + user.getOAuth().getValue() + "/" + user.getUserId() + "/" + req.getProfileImg().getOriginalFilename();
            minioUtil.putObjectToMinio(req.getProfileImg(), profileImgUrl);

            user.updateProfileImgPath(profileImgUrl);
        }

        user.updateNickName(req.getNickName());
        userRepository.save(user);
    }
}
