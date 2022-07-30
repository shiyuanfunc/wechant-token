package com.shiyuanfunc.wechat.token.manage;

import com.alibaba.fastjson.JSONObject;
import com.shiyuanfunc.wechat.token.config.CorpConfig;
import com.shiyuanfunc.wechat.token.constant.UrlConstant;
import com.shiyuanfunc.wechat.token.util.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @Author MUSI
 * @Date 2022/7/16 5:27 PM
 * @Description
 * @Version
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class WechatManage {

    private final CorpConfig corpConfig;
    private final RedisCache redisCache;
    private static final String ACCESS_TOKEN_KEY = "access_token_key";
    private static final Long ACCESS_TOKEN_EXPIRE_TIME = 2 * 60 * 60 * 1000L;
    private static final Long SAFE_TIME = 15 * 1000L;

    public String getAccessToken(){
        String accessToken = redisCache.getCache(ACCESS_TOKEN_KEY, String.class);
        if (StringUtils.isEmpty(accessToken)){
            JSONObject jsonObject = HttpUtil.get(this.buildAccessTokenUrl(), JSONObject.class);
            if (jsonObject != null){
                accessToken = jsonObject.getString("access_token");
                Long expiresIn = jsonObject.getLong("expires_in");
                redisCache.setCache(ACCESS_TOKEN_KEY, accessToken, expiresIn, TimeUnit.MILLISECONDS);
            }
        }
        return accessToken;
    }

    private String buildAccessTokenUrl() {
        return String.format(UrlConstant.wechat_access_token, corpConfig.getCorpId(), corpConfig.getCorpSecret());
    }
}
