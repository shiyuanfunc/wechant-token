package com.shiyuanfunc.wechat.token.domain.tron;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author MUSI
 * @Date 2022/11/8 10:46 PM
 * @Description
 * @Version
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TronAccountInfo implements Serializable {

    public static final String TRON_ACCOUNT_INDEX = "tron_account_info";

    private String id;

    private String privateKey;

    private String publicKey;

    private String tronAddress;

    /**
     * 用户id
     */
    private String userId;

    private Date createTime;
}
