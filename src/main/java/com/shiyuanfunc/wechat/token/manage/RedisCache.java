package com.shiyuanfunc.wechat.token.manage;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author MUSI
 * @Date 2022/7/17 10:34 PM
 * @Description
 * @Version
 **/
@Component
@RequiredArgsConstructor
public class RedisCache {

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置缓存
     * @param key
     * @param value
     * @return
     */
    public boolean setCache(String key, Object value, Long expireTime, TimeUnit timeUnit){
        if (expireTime != null && timeUnit != null){
            redisTemplate.opsForValue().set(key, value, expireTime, timeUnit);
        }else {
            redisTemplate.opsForValue().set(key, value);
        }
        return Boolean.TRUE;
    }

    /**
     * 获取缓存
     * @param key
     * @param clz
     * @return
     * @param <T>
     */
    public <T> T getCache(String key, Class<T> clz){
        Object result = redisTemplate.opsForValue().get(key);
        if (result == null){
            return null;
        }
        return JSON.parseObject(result.toString(), clz);
    }
}
