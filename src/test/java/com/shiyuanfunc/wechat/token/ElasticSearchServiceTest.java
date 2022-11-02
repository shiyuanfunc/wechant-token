package com.shiyuanfunc.wechat.token;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQueryField;
import co.elastic.clients.elasticsearch.core.CountRequest;
import co.elastic.clients.elasticsearch.core.CountResponse;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.alibaba.fastjson.JSON;
import com.shiyuanfunc.wechat.token.manage.ElasticSearchManager;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int randomInt = random.nextInt(5);
            if (randomInt < 1){
                randomInt = 1;
            }
            List<RecommendInfo> recommend_info = elasticSearchManager.queryData("recommend_info", RecommendInfo.class, randomInt, 100);
            System.out.println(recommend_info.size());
        }
    }

    @Test
    public void searchProductInfo(){

        String indexName = "gh_8adf52effae7_product";
        List<ProductDocDTO> productDocDTOS = elasticSearchManager.queryData(indexName, ProductDocDTO.class, 1, 10);
        System.out.println(JSON.toJSONString(productDocDTOS));
    }

    @Test
    public void saveMaxFieldLength(){
        String indexName = "test_max_field_length";
        ProductDocDTO productDocDTO = new ProductDocDTO();
        String str = "0435e85b60b649909dc82714cb5411f3 TESTSK000763";
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(str);
        }
        String result = StringUtils.join(list, " ");
        productDocDTO.setSkuIds(result);
        elasticSearchManager.save(productDocDTO, indexName);
    }

    @Test
    public void termQueryTest(){


        MatchQuery matchQuery = QueryBuilders.match()
                .field("describe")
                .query("时间")
                .build();
        Query query = new Query.Builder()
                .match(matchQuery)
                .build();
        SearchRequest searchRequest = new SearchRequest.Builder()
                .index("recommend_info")
                .query(query)
                .from(1)
                .size(10)
                //.sort()
                .build();
        CountRequest countRequest = new CountRequest.Builder()
                .index("recommend_info")
                .query(query)
                .build();

        try {
            CountResponse count = elasticsearchClient.count(countRequest);
            System.out.println(count.count());
            SearchResponse<RecommendInfo> search = elasticsearchClient.search(searchRequest, RecommendInfo.class);
            List<RecommendInfo> collect = search.hits().hits().stream()
                    .map(item -> {
                        RecommendInfo source = item.source();
                        String id = item.id();
                        source.setId(id);
                        return source;
                    })
                    .collect(Collectors.toList());
            System.out.println(JSON.toJSONString(collect));
        }catch (Exception ex){

        }
    }
}
