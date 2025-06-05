package com.bank.profile.kafka;

import com.bank.profile.dto.PassportDto;
import com.bank.profile.services.interfaces.PassportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PassportKafkaService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final PassportService passportService;

    @KafkaListener(topics = "passport.create", containerFactory = "passportListenerContainerFactory")
    public void handlePassportCreate(PassportDto dto,
                                     @Header(value = KafkaHeaders.REPLY_TOPIC, required = false) String replyTopic) {
        try {
            log.info("Received PassportDto for creation: {}", dto);
            PassportDto savedDto = passportService.create(dto);
            log.info("Passport created: {}", savedDto);
            if (replyTopic != null) {
                kafkaTemplate.send(replyTopic, savedDto);
            }
        } catch (Exception e) {
            log.error("Error processing Passport create: {}", e.getMessage());
            throw e;
        }
    }

    @KafkaListener(topics = "passport.update", containerFactory = "passportListenerContainerFactory")
    public void handlePassportUpdate(PassportDto dto,
                                     @Header(value = KafkaHeaders.REPLY_TOPIC, required = false) String replyTopic) {
        try {
            log.info("Received PassportDto for update: {}", dto);
            PassportDto updatedDto = passportService.update(dto.getId(), dto);
            log.info("Passport updated: {}", updatedDto);
            if (replyTopic != null) {
                kafkaTemplate.send(replyTopic, updatedDto);
            }
        } catch (Exception e) {
            log.error("Error processing Passport update: {}", e.getMessage());
            throw e;
        }
    }

    @KafkaListener(topics = "passport.delete", containerFactory = "longListenerContainerFactory")
    public void handlePassportDelete(Long id,
                                     @Header(value = KafkaHeaders.REPLY_TOPIC, required = false) String replyTopic) {
        try {
            log.info("Received Passport ID for deletion: {}", id);
            passportService.delete(id);
            log.info("Passport deleted: {}", id);
            if (replyTopic != null) {
                kafkaTemplate.send(replyTopic, id);
            }
        } catch (Exception e) {
            log.error("Error processing Passport delete: {}", e.getMessage());
            throw e;
        }
    }

    @KafkaListener(topics = "passport.get", containerFactory = "longListenerContainerFactory")
    public void handlePassportGet(Long id,
                                  @Header(value = KafkaHeaders.REPLY_TOPIC, required = false) String replyTopic) {
        try {
            log.info("Received Passport ID for retrieval: {}", id);
            PassportDto dto = passportService.getById(id);
            log.info("Passport retrieved: {}", dto);
            if (replyTopic != null) {
                kafkaTemplate.send(replyTopic, dto);
            }
        } catch (Exception e) {
            log.error("Error processing Passport get: {}", e.getMessage());
            throw e;
        }
    }
}