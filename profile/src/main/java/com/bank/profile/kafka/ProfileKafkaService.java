package com.bank.profile.kafka;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.services.interfaces.ProfileService;
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
public class ProfileKafkaService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ProfileService profileService;

    @KafkaListener(topics = "profile.create", containerFactory = "profileListenerContainerFactory")
    public void handleProfileCreate(ProfileDto dto,
                                    @Header(value = KafkaHeaders.REPLY_TOPIC, required = false) String replyTopic) {
        try {
            log.info("Received ProfileDto for creation: {}", dto);
            ProfileDto savedDto = profileService.create(dto);
            log.info("Profile created: {}", savedDto);
            if (replyTopic != null) {
                kafkaTemplate.send(replyTopic, savedDto);
            }
        } catch (Exception e) {
            log.error("Error processing Profile create: {}", e.getMessage());
            throw e;
        }
    }

    @KafkaListener(topics = "profile.update", containerFactory = "profileListenerContainerFactory")
    public void handleProfileUpdate(ProfileDto dto,
                                    @Header(value = KafkaHeaders.REPLY_TOPIC, required = false) String replyTopic) {
        try {
            log.info("Received ProfileDto for update: {}", dto);
            ProfileDto updatedDto = profileService.update(dto.getId(), dto);
            log.info("Profile updated: {}", updatedDto);
            if (replyTopic != null) {
                kafkaTemplate.send(replyTopic, updatedDto);
            }
        } catch (Exception e) {
            log.error("Error processing Profile update: {}", e.getMessage());
            throw e;
        }
    }

    @KafkaListener(topics = "profile.delete", containerFactory = "longListenerContainerFactory")
    public void handleProfileDelete(Long id,
                                    @Header(value = KafkaHeaders.REPLY_TOPIC, required = false) String replyTopic) {
        try {
            log.info("Received Profile ID for deletion: {}", id);
            profileService.delete(id);
            log.info("Profile deleted: {}", id);
            if (replyTopic != null) {
                kafkaTemplate.send(replyTopic, id);
            }
        } catch (Exception e) {
            log.error("Error processing Profile delete: {}", e.getMessage());
            throw e;
        }
    }

    @KafkaListener(topics = "profile.get", containerFactory = "longListenerContainerFactory")
    public void handleProfileGet(Long id,
                                 @Header(value = KafkaHeaders.REPLY_TOPIC, required = false) String replyTopic) {
        try {
            log.info("Received Profile ID for retrieval: {}", id);
            ProfileDto dto = profileService.getById(id);
            log.info("Profile retrieved: {}", dto);
            if (replyTopic != null) {
                kafkaTemplate.send(replyTopic, dto);
            }
        } catch (Exception e) {
            log.error("Error processing Profile get: {}", e.getMessage());
            throw e;
        }
    }
}