package com.shiyuanfunc.wechat.token;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;

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


    @Test
    public void saveIndex(){
        RecommendInfo recommendInfo = new RecommendInfo();
        recommendInfo.setBrand("天猫测试22");
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
}
