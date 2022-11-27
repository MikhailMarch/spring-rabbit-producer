package com.mikhail.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikhail.consumer.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageProducer {

    private final AmqpTemplate template;

    private ObjectMapper objectMapper = new ObjectMapper();
    private RandomDataGenerator randomDataGenerator = new RandomDataGenerator();

    @Scheduled(fixedDelay = 10000)
    public void produceRecord() throws JsonProcessingException {
        var record = generateRecord();

        String rec = objectMapper.writeValueAsString(record);
        log.info("sending record {}", rec);

        template.convertAndSend("user", rec);
    }

    public UserDto generateRecord() {
        var rand = randomDataGenerator.nextInt(0,1);

        return new UserDto(
                UserDto.Type.values()[rand],
                randomDataGenerator.nextHexString(10)
        );
    }
}
