package com.shiyuanfunc.wechat.token.manage;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author MUSI
 * @Date 2022/8/16 11:37 PM
 * @Description
 * @Version
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class ElasticSearchManager {

    private final ElasticsearchClient elasticsearchClient;

    public <T> void save(T t, String indexName){
        IndexRequest<T> indexRequest = new IndexRequest.Builder<T>()
                .index(indexName)
                .document(t)
                .build();
        try {
            elasticsearchClient.index(indexRequest);
        } catch (IOException ex) {
            log.error("[IOException] error:", ex);
        }
    }
}
