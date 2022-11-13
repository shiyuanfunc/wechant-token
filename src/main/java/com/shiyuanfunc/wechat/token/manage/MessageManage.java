package com.shiyuanfunc.wechat.token.manage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.shiyuanfunc.wechat.token.config.SpringContextUtil;
import com.shiyuanfunc.wechat.token.constant.CommandEnum;
import com.shiyuanfunc.wechat.token.manage.bot.BotCommandHandler;
import com.shiyuanfunc.wechat.token.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author MUSI
 * @Date 2022/7/19 11:18 PM
 * @Description
 * @Version
 **/
@Slf4j
public class MessageManage {

    /**
     * 聊天群组 chatId
     */
    private static String rootGroupChatId = "-1001773001656";

    public static void start() {
        initRootBotTask();
    }

    public static void sendMessageTelegram(String json) {
        String sendMessage = "https://api.telegram.org/bot5065908048:AAFmdOwkDATz2lRqbTBtc8LYe-rgmxdMOrg/sendMessage";
        Map<String, String> params = new HashMap<>();
        params.put("chat_id", rootGroupChatId);
        params.put("text", json);
        JSONObject jsonObject = HttpUtil.proxyPost(sendMessage, params, JSONObject.class);
        log.info("sendMessage result:{}", JSON.toJSONString(jsonObject));
    }


    public static void initRootBotTask() {
        String token = "5065908048:AAFmdOwkDATz2lRqbTBtc8LYe-rgmxdMOrg";
        TelegramBot bot = new TelegramBot.Builder(token)
                .okHttpClient(HttpUtil.getProxyClient())
                .build();
        bot.setUpdatesListener(listener -> {
            for (Update update : listener) {
                try {
                    if (update.message() == null) {
                        continue;
                    }
                    if (update.message().chat() == null) {
                        continue;
                    }
                    String commandText = update.message().text();
                    if (StringUtils.isBlank(commandText)) {
                        continue;
                    }
                    Long chatId = update.message().chat().id();
                    // 处理命令
                    CommandEnum commandEnum = CommandEnum.parseCommand(commandText);
                    if (CommandEnum.NO_OP.equals(commandEnum)) {
                        continue;
                    }
                    BotCommandHandler commandHandler = SpringContextUtil.getBean(commandEnum.getCommandHandler());
                    List<String> replayMessages= commandHandler.handlerBotCommand(commandEnum, commandText);
                    if (!CollectionUtils.isEmpty(replayMessages)) {
                        Integer messageId = update.message().messageId();
                        if (messageId != null) {
                            for (String replayMessage : replayMessages) {
                                replayMessage(replayMessage, chatId, messageId, bot);
                            }
                        }
                    }
                } catch (Exception ex) {
                    log.error("root telegram bot 处理消息异常", ex);
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    /**
     * 回复消息
     *
     * @param replayMsg
     * @param chatId
     * @param messageId
     * @param telegramBot
     */
    public static void replayMessage(String replayMsg, Long chatId, Integer messageId, TelegramBot telegramBot) {
        log.info("replayMessage length {}", replayMsg.length());
        String substring = StringUtils.substring(replayMsg, 0, 4096);
        SendMessage replayMessage = new SendMessage(chatId, substring);
        replayMessage.replyToMessageId(messageId);
        SendResponse sendResponse = telegramBot.execute(replayMessage);
        log.info("发送消息结果 {}", JSON.toJSONString(sendResponse));
    }
}
