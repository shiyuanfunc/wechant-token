package com.shiyuanfunc.wechat.token;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import com.alibaba.fastjson.JSON;
import com.shiyuanfunc.wechat.token.manage.ElasticSearchManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author MUSI
 * @Date 2022/8/17 9:52 PM
 * @Description
 * @Version
 **/
@SpringBootTest
public class ElasticSearchServiceTest {

    @Autowired
    private ElasticsearchClient elasticsearchClient;
    @Autowired
    private ElasticSearchManager elasticSearchManager;


    @Test
    public void saveIndex(){
        RecommendInfo recommendInfo = new RecommendInfo();
        recommendInfo.setBrand("这是一段文案 时间: 23:27");
        recommendInfo.setTime(new Date());
        IndexRequest<RecommendInfo> indexRequest = new IndexRequest.Builder<RecommendInfo>()
                .index("recommend_info")
                .document(recommendInfo)
                .build();
        try {
            elasticsearchClient.index(indexRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void searchAll(){
        List<RecommendInfo> recommend_info = elasticSearchManager.queryData("recommend_info", RecommendInfo.class);
        System.out.println(JSON.toJSONString(recommend_info));
    }
}
