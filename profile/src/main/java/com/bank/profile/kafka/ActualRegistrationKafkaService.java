package com.bank.profile.kafka;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.services.interfaces.ActualRegistrationService;
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
public class ActualRegistrationKafkaService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ActualRegistrationService actualRegistrationService;

    @KafkaListener(topics = "actual_registration.create",
                   containerFactory = "actualRegistrationListenerContainerFactory")
    public void handleActualRegistrationCreate(ActualRegistrationDto dto,
                                               @Header(value = KafkaHeaders.REPLY_TOPIC,
                                                       required = false) String replyTopic) {
        try {
            log.info("Received ActualRegistrationDto for creation: {}", dto);
            ActualRegistrationDto savedDto = actualRegistrationService.create(dto);
            log.info("ActualRegistration created: {}", savedDto);
            if (replyTopic != null) {
                kafkaTemplate.send(replyTopic, savedDto);
            }
        } catch (Exception e) {
            log.error("Error processing ActualRegistration create: {}", e.getMessage());
            throw e;
        }
    }

    @KafkaListener(topics = "actual_registration.update",
                   containerFactory = "actualRegistrationListenerContainerFactory")
    public void handleActualRegistrationUpdate(ActualRegistrationDto dto,
                                               @Header(value = KafkaHeaders.REPLY_TOPIC,
                                                       required = false) String replyTopic) {
        try {
            log.info("Received ActualRegistrationDto for update: {}", dto);
            ActualRegistrationDto updatedDto = actualRegistrationService.update(dto.getId(), dto);
            log.info("ActualRegistration updated: {}", updatedDto);
            if (replyTopic != null) {
                kafkaTemplate.send(replyTopic, updatedDto);
            }
        } catch (Exception e) {
            log.error("Error processing ActualRegistration update: {}", e.getMessage());
            throw e;
        }
    }

    @KafkaListener(topics = "actual_registration.delete", containerFactory = "longListenerContainerFactory")
    public void handleActualRegistrationDelete(Long id,
                                               @Header(value = KafkaHeaders.REPLY_TOPIC,
                                                       required = false) String replyTopic) {
        try {
            log.info("Received ActualRegistration ID for deletion: {}", id);
            actualRegistrationService.delete(id);
            log.info("ActualRegistration deleted: {}", id);
            if (replyTopic != null) {
                kafkaTemplate.send(replyTopic, id);
            }
        } catch (Exception e) {
            log.error("Error processing ActualRegistration delete: {}", e.getMessage());
            throw e;
        }
    }

    @KafkaListener(topics = "actual_registration.get", containerFactory = "longListenerContainerFactory")
    public void handleActualRegistrationGet(Long id,
                                            @Header(value = KafkaHeaders.REPLY_TOPIC,
                                                    required = false) String replyTopic) {
        try {
            log.info("Received ActualRegistration ID for retrieval: {}", id);
            ActualRegistrationDto dto = actualRegistrationService.getById(id);
            log.info("ActualRegistration retrieved: {}", dto);
            if (replyTopic != null) {
                kafkaTemplate.send(replyTopic, dto);
            }
        } catch (Exception e) {
            log.error("Error processing ActualRegistration get: {}", e.getMessage());
            throw e;
        }
    }
}