package com.shiyuanfunc.wechat.token.util;

import lombok.Data;

/**
 * @Author MUSI
 * @Date 2022/8/4 9:49 AM
 * @Description
 * @Version
 **/
public class ThreadLocalUtils {

    private static final ThreadLocal<Context> thread_local = new ThreadLocal<>();

    /**
     * 设置标签
     * @param tag
     */
    public static void putTag(String tag){
        Context context = thread_local.get();
        context.setTag(tag);
    }

    public static void putTraceId(String traceId){
        Context context = thread_local.get();
        context.setTraceId(traceId);
    }

    public static void putUserId(Long userId){
        Context context = thread_local.get();
        context.setUserId(userId);
    }

    public static void setDefault(){
        Context context = new Context();
        thread_local.set(context);
    }


    @Data
    static class Context{

        private String tag;

        private String traceId;

        private Long userId;

        private Long startTime;
    }
}
