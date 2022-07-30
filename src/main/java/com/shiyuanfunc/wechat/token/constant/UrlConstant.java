package com.shiyuanfunc.wechat.token.constant;

/**
 * @Author MUSI
 * @Date 2022/7/16 4:27 PM
 * @Description
 * @Version
 * 微信相关的地址
 **/
public class UrlConstant {

    /**
     * 微信access_token
     */
    public static String wechat_access_token = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s";

    /**
     * telegram 发消息
     */
    public static String telegram_send_message = "https://api.telegram.org/bot%s/sendMessage";
}
