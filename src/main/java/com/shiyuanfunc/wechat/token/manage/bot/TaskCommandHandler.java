package com.shiyuanfunc.wechat.token.manage.bot;

import com.shiyuanfunc.wechat.token.constant.CommandEnum;
import com.shiyuanfunc.wechat.token.manage.CrawlerManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @Author MUSI
 * @Date 2022/11/13 6:50 PM
 * @Description
 * @Version
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class TaskCommandHandler implements BotCommandHandler{

    private final CrawlerManager crawlerManager;

    /**
     * 处理的指令
     *
     * @return
     */
    @Override
    public String command() {
        return "task";
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
            return Collections.emptyList();
        }
        String childCommand = commandMsg.replaceFirst(this.command(), StringUtils.EMPTY).trim();
        if (StringUtils.equalsIgnoreCase(childCommand, "start")){
            crawlerManager.startTask();
            return Collections.singletonList("start task done");
        }
        if (StringUtils.equalsIgnoreCase(childCommand, "stop")){
            crawlerManager.cancelTask();
            return Collections.singletonList("stop task done");
        }
        return Collections.emptyList();
    }
}
