package com.shiyuanfunc.wechat.token;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author MUSI
 * @Date 2022/7/16 8:24 PM
 * @Description
 * @Version
 **/
public class Test {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("ss", 1);
        Integer ssss = (Integer) map.get("ssss");
        System.out.println(ssss);
    }
}
