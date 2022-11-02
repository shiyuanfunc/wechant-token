package com.shiyuanfunc.wechat.token.manage;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.core.CountResponse;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import co.elastic.clients.elasticsearch.core.termvectors.Term;
import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public <T> void save(T t, String indexName) {
        IndexRequest<T> indexRequest = new IndexRequest.Builder<T>()
                .index(indexName)
                .document(t)
                .build();
        try {
            IndexResponse indexResponse = elasticsearchClient.index(indexRequest);
            log.info("index response {}", JSON.toJSONString(indexResponse));
        } catch (IOException ex) {
            log.error("[IOException] error:", ex);
        }
    }

    public <T> List<T> queryData(String indexName, Class<T> clz, Integer from, Integer size) {
        try {
            SearchResponse<T> searchResponse = elasticsearchClient.search(
                    s -> s.index(indexName)
                            .query(
                                    t -> t.matchAll(
                                            new MatchAllQuery.Builder()
                                                    .build()
                                    )
                            )
                            .from(Optional.ofNullable(from).orElse(1))
                            .size(Optional.ofNullable(size).orElse(10))
                    , clz
            );
            CountResponse count = elasticsearchClient.count(t -> t.index(indexName));
            log.info("count request :{}", count.count());
            HitsMetadata<T> metadataHits = searchResponse.hits();
            List<Hit<T>> hits = metadataHits.hits();
            return hits.stream()
                    .map(Hit::source)
                    .collect(Collectors.toList());
        }catch (Exception ex){
            log.info("query es data error", ex);
        }
        return Collections.emptyList();
    }
}
