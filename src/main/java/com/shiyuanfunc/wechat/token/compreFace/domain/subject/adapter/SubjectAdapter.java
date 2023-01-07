package com.shiyuanfunc.wechat.token.compreFace.domain.subject.adapter;

import com.alibaba.fastjson.JSON;
import com.shiyuanfunc.wechat.token.compreFace.infrastructure.config.CompreFaceConfig;
import com.shiyuanfunc.wechat.token.util.HttpUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Author MUSI
 * @Date 2023/1/7 7:04 PM
 * @Description
 * @Version 主题适配器接口
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class SubjectAdapter {

    private final CompreFaceConfig compreFaceConfig;

    /**
     * 添加subject
     */
    private static final String ADD_SUBJECT_URL = "/api/v1/recognition/subjects";

    /**
     * 改名
     */
    private static final String RENAME_SUBJECT_URL = "/api/v1/recognition/subjects/%s";

    public void addSubject(String subjectName) {
        SubjectItem subjectItem = SubjectItem.buildSubject(subjectName);
        HashMap map = HttpUtil.postJson(this.buildSubjectUrl(),
                compreFaceConfig.buildSubjectHeaderMap(),
                JSON.toJSONString(subjectItem),
                HashMap.class);
        log.info("添加subject 结果:{}", JSON.toJSONString(map));
    }

    public void renameSubject(String oldSubjectName, String subjectName) {
        SubjectItem subjectItem = SubjectItem.buildSubject(subjectName);
        HashMap map = HttpUtil.putJson(this.buildRenameSubjectUrl(oldSubjectName),
                compreFaceConfig.buildSubjectHeaderMap(),
                JSON.toJSONString(subjectItem),
                HashMap.class);
        log.info("修改subject 结果:{}", JSON.toJSONString(map));
    }

    public void deleteSubject(String oldSubjectName) {
        HashMap map = HttpUtil.deleteRequest(this.buildRenameSubjectUrl(oldSubjectName),
                compreFaceConfig.buildSubjectHeaderMap(),
                null,
                HashMap.class);
        log.info("修改subject 结果:{}", JSON.toJSONString(map));
    }

    private String buildSubjectUrl() {
        return compreFaceConfig.getRootDomain() + ADD_SUBJECT_URL;
    }

    /**
     * 修改名称
     * @param subjectName
     * @return
     */
    private String buildRenameSubjectUrl(String subjectName) {
        return compreFaceConfig.getRootDomain() + String.format(RENAME_SUBJECT_URL, subjectName);
    }

    @Data
    static class SubjectItem {
        private String subject;

        /**
         * @param subjectName
         * @return
         */
        public static SubjectItem buildSubject(String subjectName) {
            SubjectItem subjectItem = new SubjectItem();
            subjectItem.setSubject(subjectName);
            return subjectItem;
        }
    }
}
