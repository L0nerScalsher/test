package com.bank.profile.ExceptionHandling;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GlobalExceptionHandler implements CommonErrorHandler {

    private final ExceptionResolutionService exceptionResolutionService;

    @Override
    public boolean handleOne(Exception thrownException, ConsumerRecord<?, ?> record,
                             Consumer<?, ?> consumer,
                             MessageListenerContainer container) {
        Throwable rootCause = getRootCause(thrownException);
        String entityType = extractEntityType(record.topic());
        log.warn("Message failed in topic {}: {} - {}", record.topic(),
                rootCause.getClass().getSimpleName(), rootCause.getMessage());


        exceptionResolutionService.sendException(entityType, rootCause);
        return true;
    }

    @Override
    public void handleOtherException(Exception thrownException, Consumer<?, ?> consumer,
                                     MessageListenerContainer container, boolean batchListener) {
        Throwable rootCause = getRootCause(thrownException);
        log.error("System error: {} - {}", rootCause.getClass().getSimpleName(), rootCause.getMessage());

        exceptionResolutionService.sendException("unknown", rootCause);

        if (consumer != null) {
            consumer.seekToEnd(consumer.assignment());
        }
        if (container != null) {
            container.resume();
        }
    }

    private String extractEntityType(String topic) {
        if (topic.contains("account_details")) return "accountdetails";
        if (topic.contains("actual_registration")) return "actualregistration";
        if (topic.contains("passport")) return "passport";
        if (topic.contains("profile")) return "profile";
        if (topic.contains("registration")) return "registration";
        return "unknown";
    }

    private Throwable getRootCause(Throwable ex) {
        while (ex.getCause() != null) {
            ex = ex.getCause();
        }
        return ex;
    }
}
