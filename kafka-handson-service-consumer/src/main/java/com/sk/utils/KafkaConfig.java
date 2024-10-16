package com.sk.utils;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Value("${kafka.topic")
    private String topicName;

//    public NewTopic newTopic(){
//        return new NewTopic(topicName,3,(short)1);
//    }
}
