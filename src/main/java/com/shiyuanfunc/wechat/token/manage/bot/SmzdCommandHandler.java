package com.shiyuanfunc.wechat.token.manage.bot;

import com.shiyuanfunc.wechat.token.constant.CommandEnum;
import com.shiyuanfunc.wechat.token.domain.recommend.RecommendInfo;
import com.shiyuanfunc.wechat.token.manage.ElasticSearchManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author MUSI
 * @Date 2022/11/13 2:45 PM
 * @Description
 * @Version
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class SmzdCommandHandler implements BotCommandHandler{

    private final ElasticSearchManager elasticSearchManager;

    /**
     * 处理的指令
     *
     * @return
     */
    @Override
    public String command() {
        return "query";
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
        String queryText = commandMsg.replaceFirst(this.command(), StringUtils.EMPTY);

        int pageNo = 1;
        int pageSize = 10;
        String[] split = StringUtils.split(queryText, StringUtils.SPACE);
        if (split.length > 1){
            pageNo = Integer.parseInt(split[1]);
        }
        if (split.length > 2){
            pageSize = Integer.parseInt(split[2]);
        }
        List<RecommendInfo> recommendInfos = elasticSearchManager.queryData("recommend_info", RecommendInfo.class, queryText, pageNo, pageSize);
        return recommendInfos.stream()
                .map(Objects::toString)
                .collect(Collectors.toList());
    }
}
