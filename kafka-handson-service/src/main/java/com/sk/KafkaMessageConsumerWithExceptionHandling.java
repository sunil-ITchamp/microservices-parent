package com.sk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class KafkaMessageConsumerWithExceptionHandling {

    @Value("${sk.kafka.topic}")
    private String topicName;

//    @RetryableTopic(attempts = "2")
//    @KafkaListener(topics = "sk-good-topic-200", groupId = "sk-group-1")
//    public void consumeEvents(User user, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.PARTITION) String partition, @Header(KafkaHeaders.RECEIVED_PARTITION) String receivedPartition, @Header(KafkaHeaders.OFFSET) long offset) {
//        try {
//            log.info("Received: {}, from topic {}, offset {}, partition {} and receivedPartition {} ", new ObjectMapper().writeValueAsString(user), topic, offset, partition, receivedPartition);
//            //validate restricted IP before process the records
//            List<String> restrictedIpList = Stream.of("32.241.244.236", "15.55.49.164", "81.1.95.253", "126.130.43.183").collect(Collectors.toList());
//            if (restrictedIpList.contains(user.getIpAddress())) {
//                throw new RuntimeException("Invalid IP Address received !");
//            }
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @DltHandler
//    public void listenDLT(User user, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,@Header(KafkaHeaders.PARTITION) String partition, @Header(KafkaHeaders.RECEIVED_PARTITION) String receivedPartition, @Header(KafkaHeaders.OFFSET) long offset) {
//        log.info("DLT Received : {} , from topic {} , offset {}, partition {} and receivedPartition {} ",user.getFirstName(),topic,offset, partition, receivedPartition);
//    }

//    @KafkaListener(topics = "${sk.kafka.topic}", groupId = "sk-group-1002")
//    public void consumeEvents20(ConsumerRecord<String, User> payload){
//        log.info("============================== consumeEvents20 ======================================");
//        log.info("Tópic: {}", topicName);
//        log.info("key: {} timestamp: {} offset: {}", payload.key() , payload.timestamp(), payload.offset());
//        log.info("Headers: {}", payload.headers());
//        log.info("Partion: {}", payload.partition());
//        log.info("Value: {}", payload.value());
//    }
//
//    @KafkaListener(topics = "${sk.kafka.topic}", groupId = "sk-group-1002")
//    public void listenWithHeaders(
//            @Payload Object message,
//            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition, @Header(KafkaHeaders.ACKNOWLEDGMENT) String ackn) {
//        System.out.println("Received Message: " + message + " from partition: " + partition + " ack = " + ackn);
//    }

}
