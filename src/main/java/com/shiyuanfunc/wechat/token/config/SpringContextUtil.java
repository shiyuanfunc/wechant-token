package com.shiyuanfunc.wechat.token.config;

import org.springframework.context.ApplicationContext;

/**
 * @Author MUSI
 * @Date 2022/8/16 11:38 PM
 * @Description
 * @Version
 **/
public class SpringContextUtil {

    private static ApplicationContext applicationContext;


    public static void setApplicationContext(ApplicationContext applicationContext){
        if (applicationContext == null){
            throw new RuntimeException("-1");
        }
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clz){
        return SpringContextUtil.applicationContext.getBean(clz);
    }
}
