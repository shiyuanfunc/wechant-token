package com.shiyuanfunc.wechat.token.manage.bot;

import com.shiyuanfunc.wechat.token.constant.CommandEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @Author MUSI
 * @Date 2022/11/13 9:42 PM
 * @Description
 * @Version
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class HelpCommandHandler implements BotCommandHandler{



    /**
     * 处理的指令
     *
     * @return
     */
    @Override
    public String command() {
        return "/help";
    }

    /**
     * 处理消息
     *
     * @param commandEnum
     * @param commandMsg
     * @return
     */
    @Override
    public List<String> handlerBotCommand(CommandEnum commandEnum, String commandMsg) {

        return Collections.emptyList();
    }
}
