package com.shiyuanfunc.wechat.token;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan(basePackages = "com.shiyuanfunc.wechat.token")
@SpringBootApplication
public class WechatTokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatTokenApplication.class, args);
    }

}
