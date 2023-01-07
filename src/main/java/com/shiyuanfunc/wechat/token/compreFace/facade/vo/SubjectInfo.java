package com.shiyuanfunc.wechat.token.compreFace.facade.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author MUSI
 * @Date 2023/1/7 8:56 PM
 * @Description
 * @Version
 **/
@Data
public class SubjectInfo implements Serializable {

    /**
     * 主题名称
     */
    private String subjectName;

    /**
     * 旧主题名称
     */
    private String oldSubjectName;
}
