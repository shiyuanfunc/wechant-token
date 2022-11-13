package com.shiyuanfunc.wechat.token.manage;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.CountResponse;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import co.elastic.clients.util.ObjectBuilder;
import com.alibaba.fastjson.JSON;
import com.shiyuanfunc.wechat.token.domain.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
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

    public <T> List<T> queryData(String indexName, Class<T> clz, String text, Integer from, Integer size) {
        try {
            SearchResponse<T> searchResponse = elasticsearchClient.search(
                    s -> s.index(indexName)
                            .query(t -> t.match(MatchQuery.of(m -> m.field("describe").query(text))))
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


    public <T> Page<T> queryWithPage(String indexName, Class<T> clz){

        Function<Query.Builder, ObjectBuilder<Query>> fn = query -> query.bool(
                bool -> bool.must(
                        q -> q.term( term -> term.field("").value(""))
                )
        );


        Query temp = new Query.Builder().build();
        BoolQuery boolQuery = temp.bool();
        boolQuery._toQuery();

        try {
            elasticsearchClient.search(
                    t -> t.index(indexName)
                            .query(fn)
                            .from(1)
                            .size(100)
                    ,
                    clz
            );
        }catch (Exception ex){

        }
        return Page.emptyPage();
    }
}
