package com.shiyuanfunc.wechat.token.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author MUSI
 * @Date 2022/7/17 10:27 PM
 * @Description
 * @Version
 **/
@Slf4j
@Data
@Configuration
public class BeanConfig {

    @Autowired
    private ElasticsearchRestClientProperties elasticsearchRestClientProperties;

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        GenericFastJsonRedisSerializer genericFastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(genericFastJsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public ElasticsearchClient elasticsearchClient(){

        log.info("elasticsearch properties :{}", JSON.toJSONString(elasticsearchRestClientProperties));
        if (CollectionUtils.isEmpty(elasticsearchRestClientProperties.getUris())){
            throw new RuntimeException("初始化失败 es地址有空");
        }
        List<HttpHost> httpHosts = elasticsearchRestClientProperties.getUris().stream()
                .map(HttpHost::create)
                .collect(Collectors.toList());
        HttpHost[] temp = new HttpHost[httpHosts.size()];
        httpHosts.toArray(temp);
        RestClientBuilder builder = RestClient.builder(temp);
        if (StringUtils.isNotBlank(elasticsearchRestClientProperties.getUsername()) && StringUtils.isNotBlank(elasticsearchRestClientProperties.getUsername())) {
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(elasticsearchRestClientProperties.getUsername(), elasticsearchRestClientProperties.getPassword()));
            builder.setHttpClientConfigCallback(httpClientBuilder -> {
                httpClientBuilder.disableAuthCaching();
                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            });
        }
        RestClient restClient = builder.build();
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }
//
//
//    @Bean
//    public ElasticsearchClient elasticsearchClient2(){
//        RestClientBuilder builder = RestClient.builder(new HttpHost("http://www.gicdev.com/test-es", 80));
//        if (StringUtils.isNotBlank(elasticsearchRestClientProperties.getUsername()) && StringUtils.isNotBlank(elasticsearchRestClientProperties.getUsername())) {
//            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(elasticsearchRestClientProperties.getUsername(), elasticsearchRestClientProperties.getPassword()));
//            builder.setHttpClientConfigCallback(httpClientBuilder -> {
//                httpClientBuilder.disableAuthCaching();
//                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//            });
//        }
//        RestClient restClient = builder.build();
//        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
//        return new ElasticsearchClient(transport);
//    }
}
