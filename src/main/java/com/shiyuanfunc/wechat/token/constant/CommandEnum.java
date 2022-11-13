package com.shiyuanfunc.wechat.token.constant;

import com.shiyuanfunc.wechat.token.manage.bot.BotCommandHandler;
import com.shiyuanfunc.wechat.token.manage.bot.SmzdCommandHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 操作类型
 *
 * @Author MUSI
 * @Date 2022/11/13 12:31 AM
 * @Description
 * @Version
 **/
@Getter
@AllArgsConstructor
public enum CommandEnum {



    // add bot token
    ADD_TOKEN("addToken", "添加新Bot token", BotCommandHandler.class),

    // 查询ES 数据
    QUERY_TEXT("query", "查询ES 数据", SmzdCommandHandler.class),
    // 无需操作
    NO_OP("no_op", "无需操作", BotCommandHandler.class);

    /**
     * 命令
     */
    private final String command;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 命令处理器
     */
    private final Class<? extends  BotCommandHandler> commandHandler;

    /**
     * 获取命令类型
     * @param code
     * @return
     */
    public static CommandEnum getCommandByCode(String code){
        for (CommandEnum commandEnum : values()) {
            if (StringUtils.equalsIgnoreCase(code, commandEnum.getCommand())){
                return commandEnum;
            }
        }
        return CommandEnum.NO_OP;
    }

    /**
     * 解析命令
     * @param commandText
     * @return
     */
    public static CommandEnum parseCommand(String commandText){

        if (StringUtils.isBlank(commandText)){
            return CommandEnum.NO_OP;
        }
        if (!StringUtils.contains(commandText.trim(), StringUtils.SPACE)){
            return CommandEnum.NO_OP;
        }
        String[] commandArry = StringUtils.split(commandText.trim(), StringUtils.SPACE);
        return CommandEnum.getCommandByCode(commandArry[0]);
    }
}
