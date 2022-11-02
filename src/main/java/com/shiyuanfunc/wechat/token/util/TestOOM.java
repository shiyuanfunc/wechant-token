package com.shiyuanfunc.wechat.token.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author MUSI
 * @Date 2022/8/4 9:48 AM
 * @Description
 * @Version
 **/
@Component
public class TestOOM {

    private static final Integer size = 1024 * 1024;


    private boolean show;


    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public void oonTest(){
        ThreadLocalUtils.setDefault();
        List<String> list = new ArrayList<>();
        String traceId = UUID.randomUUID().toString();
        ThreadLocalUtils.putUserId(13L);
        ThreadLocalUtils.putTraceId(traceId);
        ThreadLocalUtils.putTag("tag" + 13);
        List<Object> objects = new ArrayList<>();
        while (true){
            list.add(UUID.randomUUID().toString());
            byte[] bytes = new byte[size];
            objects.add(bytes);
        }
    }
}
