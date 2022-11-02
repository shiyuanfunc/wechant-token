package com.shiyuanfunc.wechat.token.util;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询条件
 *
 * @Author MUSI
 * @Date 2022/10/29 10:01 PM
 * @Description
 * @Version
 **/

@Data
public class SearchCondition implements Serializable {

    /**
     *  操作类型 terms term should
     */
    private Operator operator;

    private String field;

    private Object value;

    private List<SearchCondition> childCondition = new ArrayList<>();

    public SearchCondition(Operator operator, String field, Object value){
        this.operator = operator;
        this.field = field;
        this.value = value;
    }

}

