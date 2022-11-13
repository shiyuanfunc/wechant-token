package com.shiyuanfunc.wechat.token.scheduler;

import com.shiyuanfunc.wechat.token.manage.CrawlerManager;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author MUSI
 * @Date 2022/7/30 11:58 PM
 * @Description
 * @Version
 **/
@Component
@EnableScheduling
@RequiredArgsConstructor
public class TaskJob {


    private final CrawlerManager crawlerManager;
    private static final Long delay_time = 1000 * 60 * 45L;

    /**
     * 什么值得买 好价
     */
    //@Scheduled(fixedRate = 1000 * 60 * 45)
    public void getSMZDMGoodsPrice() {
        crawlerManager.crawler_jingxuan();
    }
}
