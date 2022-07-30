package com.shiyuanfunc.wechat.token.manage;

import com.shiyuanfunc.wechat.token.crawler.CrawlerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public void crawler_jingxuan(){
        long startTime = System.currentTimeMillis();
        log.info("crawler_jingxuan execute time:{} >>>>>> ", LocalDateTime.now().format(formatter));
        CrawlerUtil.crawlerWithUrl(CrawlerUtil.goods_price);
        long endTime = System.currentTimeMillis();
        log.info("cost:{}", (endTime - startTime));
    }
}
