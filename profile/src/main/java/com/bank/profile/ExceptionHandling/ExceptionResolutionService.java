package com.bank.profile.ExceptionHandling;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExceptionResolutionService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendException(String entityType, Throwable throwable) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                throwable.getClass().getSimpleName(),
                throwable.getMessage() != null ? throwable.getMessage() : "No message provided"
        );
        String errorTopic = determineErrorTopic(entityType);
        Message<ErrorResponseDto> message = MessageBuilder
                .withPayload(errorResponseDto)
                .setHeader(KafkaHeaders.TOPIC, errorTopic)
                .build();
        kafkaTemplate.send(message);
    }

    private String determineErrorTopic(String entityType) {
        return switch (entityType.toLowerCase()) {
            case "accountdetails" -> "account_details.errors";
            case "actualregistration" -> "actual_registration.errors";
            case "passport" -> "passport.errors";
            case "profile" -> "profile.errors";
            case "registration" -> "registration.errors";
            default -> "unknown.errors";
        };
    }
}
