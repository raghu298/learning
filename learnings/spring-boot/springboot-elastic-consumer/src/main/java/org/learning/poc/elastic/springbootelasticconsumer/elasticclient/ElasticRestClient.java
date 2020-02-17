package org.learning.poc.elastic.springbootelasticconsumer.elasticclient;

import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ElasticRestClient {

AtomicInteger count = new AtomicInteger();
    private RestHighLevelClient client() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
    }


    public String createIndex(String indeName, String requestBody) {
        log.info("Index created ", indeName );
        IndexRequest request = new IndexRequest(indeName)
                .id(String.valueOf(count.incrementAndGet()))
                .source(requestBody, XContentType.JSON)
                .type("test")
                .opType(DocWriteRequest.OpType.CREATE);
        try {
            IndexResponse response = client().index(request, RequestOptions.DEFAULT);
            return response.status().name();
        } catch (ElasticsearchException | IOException e) {
            log.error(" Erron in creating index", e);
            return "Falied";
        }
    }

    public String updateIndex(String indeName) {
        IndexRequest request = new IndexRequest(indeName)
                .id("1")
                .source("field", "value")
                .opType(DocWriteRequest.OpType.CREATE);
        try {
            IndexResponse response = client().index(request, RequestOptions.DEFAULT);
            return response.status().name();
        } catch (ElasticsearchException | IOException e) {
            log.error(" Erron in creating index", e);
            return "Falied";
        }
    }
}



