package com.shiyuanfunc.wechat.token.crawler;

import com.alibaba.fastjson.JSONObject;
import com.shiyuanfunc.wechat.token.manage.SendMessageManage;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Author MUSI
 * @Date 2022/7/19 9:39 PM
 * @Description
 * @Version
 **/
@Slf4j
public class CrawlerUtil {

    public static final String goods_price = "https://www.smzdm.com/jingxuan";

    public static void main(String[] args) throws IOException {

        // 好价
        String url = "https://www.smzdm.com/jingxuan";

        //活动
        //String url = "https://www.smzdm.com/jingxuan/xuan/s0f0t0b0d0r3p1";
        // 商品搜索
        //String url = "https://search.smzdm.com/?c=faxian&s=服务器";
        crawlerWithUrl(url);
    }

    public static void crawlerWithUrl(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select(".feed-block");

            for (Element element : elements) {
                // 商品对象数组
                JSONObject jsonObject = new JSONObject();
                element.children().forEach(temp -> {
                    if (temp.hasClass("z-feed-img")) {
                        //头图
                        Element aElement = temp.selectFirst("a");
                        if (aElement != null) {
                            String href = aElement.attr("href");
                            jsonObject.put("href", href);
                            Element img = aElement.selectFirst("img");
                            if (img != null) {
                                String imgUrl = img.attr("src");
                                jsonObject.put("imgUrl", imgUrl);
                            }
                        }
                    }
                    if (temp.hasClass("z-feed-content")) {
                        // 内容
                        if (temp.childNodeSize() > 0) {
                            for (Element child : temp.children()) {
                                if (child.hasClass("feed-block-title")) {
                                    // title
                                    String title = child.text();
                                    jsonObject.put("title", title);
                                }
                                if (child.hasClass("z-highlight")) {
                                    // 高亮
                                    String price = child.text();
                                    jsonObject.put("price", price);
                                }
                                if (child.hasClass("feed-block-descripe")) {
                                    // 描述
                                    String descripe = child.text();
                                    jsonObject.put("descripe", descripe);
                                }
                                if (child.hasClass("z-feed-foot")) {
                                    // 附加信息
                                    Element extract = child.selectFirst(".feed-block-extras");
                                    if (extract != null) {
                                        jsonObject.put("timeStr", extract.childNode(0).toString());
                                        Element sourceBrand = extract.selectFirst("a");
                                        if (sourceBrand != null) {
                                            String brand = sourceBrand.text();
                                            jsonObject.put("brand", brand);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
                if (!jsonObject.isEmpty()) {
                    log.info(jsonObject.toJSONString());
                    SendMessageManage.sendMessageTelegram(jsonObject.toJSONString());
                }
                log.info("######################");
            }
        } catch (Exception ex) {
            log.error("解析url:{}", url, ex);
        }
    }
}