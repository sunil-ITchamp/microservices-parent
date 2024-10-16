package com.sk;

import com.sk.utils.CsvReaderUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/event")
@Slf4j
public class EventController {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${sk.kafka.topic}")
    private String topicName;

    @PostMapping("/publish")
    public ResponseEntity<String> publishUsers(@RequestBody User user) throws IOException {
        List<User> userList = CsvReaderUtils.getUsersFromCsv();
        System.out.println(userList.size());
        userList.forEach(userObject->{
            CompletableFuture<SendResult<String, Object>> completableFuture = kafkaTemplate.send(topicName, userObject);
            completableFuture.whenComplete((result, exception)->{
               if (Objects.isNull(exception)){
                    log.info(" Producer - Record = {}", result.getProducerRecord().toString());;
                   log.info(" Record-Metadata = {}", result.getRecordMetadata().toString());
               }
            });
        });
        return ResponseEntity.ok("Users List published successfully...");
    }

    @PostMapping("/publishuser")
    public ResponseEntity<String> publishUser(@RequestBody User user) throws IOException {
            CompletableFuture<SendResult<String, Object>> completableFuture = kafkaTemplate.send(topicName, user);
            completableFuture.whenComplete((result, exception)->{
                if (Objects.isNull(exception)){
                    log.info("Sent message=[" + ((User)result.getProducerRecord().value()).toString() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]" +
                            "to partition=[" + result.getRecordMetadata().partition() + "]");

                }
            });
        return ResponseEntity.ok("User published successfully...");
    }
}
