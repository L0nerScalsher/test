package com.bank.profile.kafka;

import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.services.interfaces.RegistrationService;
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
public class RegistrationKafkaService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final RegistrationService registrationService;

    @KafkaListener(topics = "registration.create", containerFactory = "registrationListenerContainerFactory")
    public void handleRegistrationCreate(RegistrationDto dto,
                                         @Header(value = KafkaHeaders.REPLY_TOPIC, required = false) String replyTopic) {
        try {
            log.info("Received RegistrationDto for creation: {}", dto);
            RegistrationDto savedDto = registrationService.create(dto);
            log.info("Registration created: {}", savedDto);
            if (replyTopic != null) {
                kafkaTemplate.send(replyTopic, savedDto);
            }
        } catch (Exception e) {
            log.error("Error processing Registration create: {}", e.getMessage());
            throw e;
        }
    }

    @KafkaListener(topics = "registration.update", containerFactory = "registrationListenerContainerFactory")
    public void handleRegistrationUpdate(RegistrationDto dto,
                                         @Header(value = KafkaHeaders.REPLY_TOPIC, required = false) String replyTopic) {
        try {
            log.info("Received RegistrationDto for update: {}", dto);
            RegistrationDto updatedDto = registrationService.update(dto.getId(), dto);
            log.info("Registration updated: {}", updatedDto);
            if (replyTopic != null) {
                kafkaTemplate.send(replyTopic, updatedDto);
            }
        } catch (Exception e) {
            log.error("Error processing Registration update: {}", e.getMessage());
            throw e;
        }
    }

    @KafkaListener(topics = "registration.delete", containerFactory = "longListenerContainerFactory")
    public void handleRegistrationDelete(Long id,
                                         @Header(value = KafkaHeaders.REPLY_TOPIC, required = false) String replyTopic) {
        try {
            log.info("Received Registration ID for deletion: {}", id);
            registrationService.delete(id);
            log.info("Registration deleted: {}", id);
            if (replyTopic != null) {
                kafkaTemplate.send(replyTopic, id);
            }
        } catch (Exception e) {
            log.error("Error processing Registration delete: {}", e.getMessage());
            throw e;
        }
    }

    @KafkaListener(topics = "registration.get", containerFactory = "longListenerContainerFactory")
    public void handleRegistrationGet(Long id,
                                      @Header(value = KafkaHeaders.REPLY_TOPIC, required = false) String replyTopic) {
        try {
            log.info("Received Registration ID for retrieval: {}", id);
            RegistrationDto dto = registrationService.getById(id);
            log.info("Registration retrieved: {}", dto);
            if (replyTopic != null) {
                kafkaTemplate.send(replyTopic, dto);
            }
        } catch (Exception e) {
            log.error("Error processing Registration get: {}", e.getMessage());
            throw e;
        }
    }
}