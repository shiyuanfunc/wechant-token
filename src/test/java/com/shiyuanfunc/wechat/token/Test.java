package com.shiyuanfunc.wechat.token;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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


    @org.junit.jupiter.api.Test
    public void splitString(){
        String str = "_addadad_addada_adad_";
        String[] split = StringUtils.split(str, "_");
        for (String s : split) {
            System.out.println(s);
        }
    }


    @org.junit.jupiter.api.Test
    public void simpleTest(){
        String str = "ff80808182ad28ae01835e0f888815ca ff80808182ad28ae01835e0f7b481477 ff8080818438afb401845035d5490f5c ff8080818438afb401845035d9330f91 ff8080818438afb401845035dcad0fc1 ff8080818438b04b018450239d5b43af ff8080818438afb401845035c18d0e82 ff8080818438afb401845035da2e0fa0 ff8080818438b04b018450238f87437f ff8080818438b04b0184502390fe438c ff8080818438b04b018450237bff4327 ff8080818438b04b018450237a0c4315 ff8080818438b04b0184502376fc42fb ff8080818438b04b01845023709442d7 ff8080818438b04b0184502378844308 ff8080818438b04b018450238c794372 ff8080818438b04b018450236d5742bd ff8080818438b04b018450236f0842ca ff80808182ad28ae01835e0f86e51596 ff80808182ad28ae01835e0f93fd16d4 ff80808182ad28ae01835e0f82e31521 ff80808182ad28ae01835e0f861d157f ff80808182ad28ae01835e0f85731568 ff80808182ad28ae01835e0f837b1533 ff80808182ad28ae01835e0f93b116cb ff80808182ad28ae01835e0f876115a7 ff80808182ad28ae01835e0f7956143c ff80808182ad28ae01835e0f7a841461 ff80808182ad28ae01835e0f790a142c ff80808182ad28ae01835e0f8334152a ff80808182ad28ae01835e0f804c14f0 ff80808182ad28ae01835e0f7a0d144c ff80808182ad28ae01835e0f7ffd14e7 ff80808182ad28ae01835e0f845b1547 ff80808182ad28ae01835e0f7896141d";
        String[] split = StringUtils.split(str, " ");
        List<String> list = Arrays.asList(split);
        System.out.println(JSON.toJSONString(list));
    }
}

