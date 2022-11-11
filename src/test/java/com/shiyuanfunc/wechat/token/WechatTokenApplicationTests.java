package com.shiyuanfunc.wechat.token;

import com.shiyuanfunc.wechat.token.tron.TronClientManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WechatTokenApplicationTests {

    @Autowired
    private TronClientManager tronClientManager;

    @Test
    void contextLoads() {
    }

    @Test
    public void saveTronAccount(){
        //System.out.println(tronClientManager.generateAddress());
        try {
            Object o = tronClientManager.sendTransaction();
            System.out.println(o.toString());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void transferTrc20(){
        Object o = tronClientManager.balanceOfAddress("TYuVKUAvXesMFJLLNiGEw5kWVzwkhEEoE5");
        System.out.println(o.toString());
    }

}
