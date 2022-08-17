package com.shiyuanfunc.wechat.token.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author MUSI
 * @Date 2022/8/16 11:40 PM
 * @Description
 * @Version
 **/
@Component
public class SpringApplicationContext implements ApplicationContextAware {


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.setApplicationContext(applicationContext);
    }
}
