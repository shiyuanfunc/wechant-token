package com.shiyuanfunc.wechat.token.manage;

import com.shiyuanfunc.wechat.token.crawler.CrawlerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author MUSI
 * @Date 2022/7/31 12:04 AM
 * @Description
 * @Version
 **/
@Slf4j
@Component
public class CrawlerManager {

    public static final  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    Timer timer = null;
    public void crawler_jingxuan(){
        long startTime = System.currentTimeMillis();
        log.info("crawler_jingxuan execute time:{} >>>>>> ", LocalDateTime.now().format(formatter));
        CrawlerUtil.crawlerWithUrl(CrawlerUtil.goods_price);
        long endTime = System.currentTimeMillis();
        log.info("cost:{}", (endTime - startTime));
    }

    /**
     * 启动定时任务
     */
    public void startTask(){
        if (timer == null){
            timer = new Timer("crawler_task", true);
        }
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                log.info("启动定时任务 >>>>>>> ");
                CrawlerManager.this.crawler_jingxuan();
            }
        }, 10 * 1000, 32 * 60 * 1000);
    }

    /**
     * 取消任务
     */
    public void cancelTask(){
        if (timer != null){
            timer.cancel();
        }
    }
}
