package com.shiyuanfunc.wechat.token.manage.bot;

import com.shiyuanfunc.wechat.token.constant.CommandEnum;

import java.util.List;

/**
 * 机器人命令
 * @Author MUSI
 * @Date 2022/11/13 12:42 AM
 * @Description
 * @Version
 **/
public interface BotCommandHandler {

    /**
     * 处理的指令
     * @return
     */
    String command();

    /**
     * 处理消息
     * @param commandEnum
     * @param commandMsg
     * @return
     */
    List<String> handlerBotCommand(CommandEnum commandEnum, String commandMsg);
}
