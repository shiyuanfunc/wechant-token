package com.shiyuanfunc.wechat.token.util;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author MUSI
 * @Date 2022/8/1 6:30 PM
 * @Description
 * @Version
 **/
public class FileUtils {


    public static void main(String[] args) throws Exception{
        File file = new File("/Users/songxiaohui/Documents/dev/workspace/sharding-demo/wechat-token/src/main/resources/SSR.txt");
        String s = IOUtils.toString(Files.newInputStream(file.toPath()), Charset.defaultCharset());
        byte[] bytes = com.alibaba.fastjson.util.IOUtils.decodeBase64(s);
        //System.out.println(new String(bytes));
        String[] split = new String(bytes).split("\\n");
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(split));
        System.out.println(set.size());
        set.forEach(System.out::println);
    }
}
