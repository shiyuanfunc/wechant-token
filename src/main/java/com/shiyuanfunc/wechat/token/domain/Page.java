package com.shiyuanfunc.wechat.token.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @Author MUSI
 * @Date 2022/11/4 10:13 PM
 * @Description
 * @Version
 **/
@Data
public class Page<T> implements Serializable {

    private int total;

    private List<T> data;

    public static <T> Page<T> of(int total, List<T> data){
        Page<T> page = new Page<>();
        page.setTotal(total);
        page.setData(data);
        return page;
    }

    public static <T> Page<T> emptyPage(){
        Page<T> page = new Page<>();
        page.setTotal(0);
        page.setData(Collections.emptyList());
        return page;
    }

    public void test(){

    }
}
