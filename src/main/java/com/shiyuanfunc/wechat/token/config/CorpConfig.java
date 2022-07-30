package com.shiyuanfunc.wechat.token.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author MUSI
 * @Date 2022/7/16 5:28 PM
 * @Description
 * @Version
 **/
@Data
@Component
@ConfigurationProperties(prefix = "wechat.config")
public class CorpConfig {
    private String corpId;
    private String corpSecret;
}
