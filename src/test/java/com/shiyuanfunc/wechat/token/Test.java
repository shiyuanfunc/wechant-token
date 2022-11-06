package com.shiyuanfunc.wechat.token;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author MUSI
 * @Date 2022/7/16 8:24 PM
 * @Description
 * @Version
 **/
public class Test {

    private static final Logger log = LogManager.getLogger(Test.class);

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("ss", 1);
        Integer ssss = (Integer) map.get("ssss");
        System.out.println(ssss);
        System.out.println((true && true || false) && false);

        log.error("${java:os}");
    }


    @org.junit.jupiter.api.Test
    public void logTest() {
        log.info("${java:os}");
    }
}

