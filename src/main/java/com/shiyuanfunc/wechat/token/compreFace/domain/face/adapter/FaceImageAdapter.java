package com.shiyuanfunc.wechat.token.compreFace.domain.face.adapter;

import com.shiyuanfunc.wechat.token.compreFace.infrastructure.config.CompreFaceConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author MUSI
 * @Date 2023/1/7 11:37 PM
 * @Description
 * @Version
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class FaceImageAdapter {

    private final CompreFaceConfig compreFaceConfig;

    private static final String UPLOAD_FACE_IMAGE = "/api/v1/recognition/faces";

    public Object addFaceImage() {
        return null;
    }
}
