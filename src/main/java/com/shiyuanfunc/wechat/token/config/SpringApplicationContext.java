package com.shiyuanfunc.wechat.token.config;

import com.shiyuanfunc.wechat.token.manage.MessageManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author MUSI
 * @Date 2022/8/16 11:40 PM
 * @Description
 * @Version
 **/
@Slf4j
@Component
public class SpringApplicationContext implements ApplicationContextAware, ApplicationListener<ApplicationStartedEvent> {


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.setApplicationContext(applicationContext);
    }

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("application started >>>>>");
        MessageManage.start();
    }
}
