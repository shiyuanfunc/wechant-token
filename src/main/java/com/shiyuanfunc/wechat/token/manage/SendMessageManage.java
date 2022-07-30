package com.shiyuanfunc.wechat.token.manage;

import com.alibaba.fastjson.JSONObject;
import com.shiyuanfunc.wechat.token.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author MUSI
 * @Date 2022/7/19 11:18 PM
 * @Description
 * @Version
 **/
public class SendMessageManage {


    public static void sendMessageTelegram(String json){

        String sendMessage = "https://api.telegram.org/bot5065908048:AAFmdOwkDATz2lRqbTBtc8LYe-rgmxdMOrg/sendMessage";
        Map<String, String> params = new HashMap<>();
        params.put("chat_id", "-737850981");
        params.put("text", json);
        JSONObject jsonObject = HttpUtil.proxyPost(sendMessage, params, JSONObject.class);
    }
}
