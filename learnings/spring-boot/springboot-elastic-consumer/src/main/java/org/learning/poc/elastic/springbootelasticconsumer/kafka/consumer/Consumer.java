package org.learning.poc.elastic.springbootelasticconsumer.kafka.consumer;

import org.learning.poc.elastic.springbootelasticconsumer.elasticclient.ElasticRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Consumer {

    @Autowired
    private ElasticRestClient restClient;
    @KafkaListener(topics = "CREATE_DEVICE", groupId = "group-id")
    public void consume(String message){
        log.info(String.format("$$ -> Consumed Message -> %s",message));
        restClient.createIndex("createdevice", message);

    }
}
