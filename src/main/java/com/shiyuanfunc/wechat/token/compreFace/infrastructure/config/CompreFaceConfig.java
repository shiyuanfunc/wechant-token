package com.shiyuanfunc.wechat.token.compreFace.infrastructure.config;

import com.google.common.collect.Maps;
import com.shiyuanfunc.wechat.token.compreFace.infrastructure.enums.SubjectApiKeyEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author MUSI
 * @Date 2023/1/7 8:48 PM
 * @Description
 * @Version
 * compreFace 配置信息
 **/
@Data
@Component
@ConfigurationProperties(value = "spring.compre.face")
public class CompreFaceConfig {

    private String rootDomain;

    /**
     * 服务api key
     */
    private Map<String, String> serviceApiKeyMap;

    /**
     * subject header map
     * @return
     */
    public Map<String, String> buildSubjectHeaderMap(){
        Map<String, String> headerMap = Maps.newHashMapWithExpectedSize(2);
        String apiKey = serviceApiKeyMap.get(SubjectApiKeyEnum.subject.name());
        headerMap.put("x-api-key", apiKey);
        return headerMap;
    }

}
