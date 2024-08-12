package com.woo.backend.domain.user.dto.req;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProfileUpdateReq {
    private String nickName;
    private MultipartFile profileImg;
}
