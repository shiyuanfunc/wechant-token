package com.shiyuanfunc.wechat.token.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author MUSI
 * @Date 2022/7/16 4:32 PM
 * @Description
 * @Version
 **/
@Slf4j
public class HttpUtil {

    private static volatile OkHttpClient client = null;
    private static volatile OkHttpClient proxyClient = null;
    public static final MediaType MEDIA_JSON_TYPE = MediaType.parse("application/json; charset=utf-8");


    static {
        initClient();
    }

    private static void initClient(){
        client = new OkHttpClient.Builder()
                .callTimeout(Duration.ofSeconds(10))
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    private static void initProxyClient(){
        InetSocketAddress inetSocketAddress = new InetSocketAddress("192.168.31.65", 1087);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, inetSocketAddress);
        proxyClient = new OkHttpClient.Builder()
                .callTimeout(Duration.ofSeconds(10))
                .connectTimeout(5, TimeUnit.SECONDS)
                .proxy(proxy)
                .build();
    }

    private static OkHttpClient getClient(){
        if (client == null){
            synchronized (HttpUtil.class){
                if (client == null){
                    initClient();
                }
            }
        }
        return client;
    }

    private static OkHttpClient getProxyClient(){
        if (proxyClient == null){
            synchronized (HttpUtil.class){
                if (proxyClient == null){
                    initProxyClient();
                }
            }
        }
        return proxyClient;
    }

    public static <T> T get(String url, Class<T> clz) {
        return doGet(url, clz, false);
    }

    public static <T> T getProxy(String url, Class<T> clz) {
        return doGet(url, clz, true);
    }

    private static <T> T doGet(String url, Class<T> clz, boolean useProxy){
        OkHttpClient httpClient = useProxy ? getProxyClient() : getClient();
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response = httpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if (responseBody != null){
                String result = responseBody.string();
                if (!StringUtils.isEmpty(request)){
                    return JSON.parseObject(result, clz);
                }
            }
        } catch (Exception ex) {
            log.error("[HttpUtil.get] request err", ex);
        }
        return null;
    }


    public static <T> T post(String url, Map<String, String> params, Class<T> clz){
        return doPostForm(url, params, clz, false);
    }

    public static <T> T proxyPost(String url, Map<String, String> params, Class<T> clz){
        return doPostForm(url, params, clz, true);
    }

    private static <T> T doPostForm(String url, Map<String, String> params, Class<T> clz, boolean useProxy){
        try{
            FormBody.Builder builder = new FormBody.Builder();
            if (!params.isEmpty()){
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    builder.add(entry.getKey(), entry.getValue());
                }
            }
            FormBody formBody = builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();
            OkHttpClient executeClient = useProxy ? getProxyClient() : getClient();
            Response response = executeClient.newCall(request).execute();
            ResponseBody body = response.body();
            if (body != null) {
                return JSONObject.parseObject(body.string(), clz);
            }
        }catch (Exception ex){

        }
        return null;
    }

    private static <T> T doPostJson(String url, String paramsJson, Class<T> clz, boolean useProxy){
        OkHttpClient httpClient = useProxy ? getProxyClient() : getClient();
        try{
            RequestBody requestBody = RequestBody.create(paramsJson, MEDIA_JSON_TYPE);
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
        }catch (Exception ex){

        }
        return null;
    }


    public static void main(String[] args) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 1087);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, inetSocketAddress);
        OkHttpClient client = new OkHttpClient.Builder()
                .callTimeout(Duration.ofSeconds(10))
                .connectTimeout(5, TimeUnit.SECONDS)
                .proxy(proxy)
                .build();

        String url = "https://api.telegram.org/bot5065908048:AAFmdOwkDATz2lRqbTBtc8LYe-rgmxdMOrg/getMe";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response execute = client.newCall(request).execute();
        String string = execute.body().string();
        System.out.println(string);
    }
}
