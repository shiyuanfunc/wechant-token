package com.shiyuanfunc.wechat.token.manage;

import com.shiyuanfunc.wechat.token.util.TestOOM;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author MUSI
 * @Date 2022/8/4 9:57 AM
 * @Description
 * @Version
 **/
@RequiredArgsConstructor
@RequestMapping(path = "/test")
@RestController
public class TestController {


    private final TestOOM testOOM;


    @GetMapping(path = "/oom")
    public Object test(){
        testOOM.oonTest();
        return "SUCCESS";
    }
}
