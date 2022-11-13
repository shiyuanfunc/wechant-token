package com.shiyuanfunc.wechat.token.manage.bot;

import com.shiyuanfunc.wechat.token.constant.CommandEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author MUSI
 * @Date 2022/11/13 2:41 PM
 * @Description
 * @Version
 **/
@Slf4j
@Component
public class AddTokenCommandHandler implements BotCommandHandler{


    /**
     * 处理的指令
     *
     * @return
     */
    @Override
    public String command() {
        return "addToken";
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
        if (!StringUtils.equalsIgnoreCase(commandEnum.getCommand(), this.command())){
            return null;
        }
        // 添加一个token
        return null;
    }
}
