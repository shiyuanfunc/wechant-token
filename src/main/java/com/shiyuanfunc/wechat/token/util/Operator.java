package com.shiyuanfunc.wechat.token.util;

import lombok.Getter;

@Getter
public enum Operator {
    TERM, TERMS, RANGE, FUZZY, QUERY_STRING, MISSING
}
