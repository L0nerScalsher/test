package com.bank.profile.kafka;

import com.bank.profile.ExceptionHandling.GlobalExceptionHandler;
import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.dto.PassportDto;
import com.bank.profile.dto.ProfileDto;
import com.bank.profile.dto.RegistrationDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@EnableKafka
@Configuration
public class KafkaConfig {

    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private GlobalExceptionHandler globalExceptionHandler;

    @Autowired
    public void setGlobalExceptionHandler(@Lazy GlobalExceptionHandler globalExceptionHandler) {
        this.globalExceptionHandler = globalExceptionHandler;
    }

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(GROUP_ID_CONFIG, "group_id");
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setCommonErrorHandler(globalExceptionHandler);
        return factory;
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> pf) {
        return new KafkaTemplate<>(pf);
    }

    @Bean
    public ConsumerFactory<String, ProfileDto> profileFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(GROUP_ID_CONFIG, "profile-consumer-group");
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.bank.profile.dto");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(ProfileDto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProfileDto> profileListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ProfileDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(profileFactory());
        factory.setCommonErrorHandler(globalExceptionHandler);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, PassportDto> passportFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(GROUP_ID_CONFIG, "passport-consumer-group");
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.bank.profile.dto");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(PassportDto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PassportDto> passportListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PassportDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(passportFactory());
        factory.setCommonErrorHandler(globalExceptionHandler);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, RegistrationDto> registrationFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(GROUP_ID_CONFIG, "registration-consumer-group");
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.bank.profile.dto");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(RegistrationDto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RegistrationDto> registrationListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, RegistrationDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(registrationFactory());
        factory.setCommonErrorHandler(globalExceptionHandler);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, ActualRegistrationDto> actualRegistrationFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(GROUP_ID_CONFIG, "actual-registration-consumer-group");
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.bank.profile.dto");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(ActualRegistrationDto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ActualRegistrationDto> actualRegistrationListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ActualRegistrationDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(actualRegistrationFactory());
        factory.setCommonErrorHandler(globalExceptionHandler);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Long> longFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(GROUP_ID_CONFIG, "long-consumer-group");
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "java.lang");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Long.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Long> longListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Long> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(longFactory());
        factory.setCommonErrorHandler(globalExceptionHandler);
        return factory;
    }

    @Bean
    public NewTopic profileCreateTopic() {
        return TopicBuilder.name("profile.create").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic profileUpdateTopic() {
        return TopicBuilder.name("profile.update").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic profileDeleteTopic() {
        return TopicBuilder.name("profile.delete").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic profileGetTopic() {
        return TopicBuilder.name("profile.get").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic profileErrorsTopic() {
        return TopicBuilder.name("profile.errors").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic passportCreateTopic() {
        return TopicBuilder.name("passport.create").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic passportUpdateTopic() {
        return TopicBuilder.name("passport.update").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic passportDeleteTopic() {
        return TopicBuilder.name("passport.delete").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic passportGetTopic() {
        return TopicBuilder.name("passport.get").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic passportErrorsTopic() {
        return TopicBuilder.name("passport.errors").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic registrationCreateTopic() {
        return TopicBuilder.name("registration.create").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic registrationUpdateTopic() {
        return TopicBuilder.name("registration.update").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic registrationDeleteTopic() {
        return TopicBuilder.name("registration.delete").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic registrationGetTopic() {
        return TopicBuilder.name("registration.get").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic registrationErrorsTopic() {
        return TopicBuilder.name("registration.errors").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic actualRegistrationCreateTopic() {
        return TopicBuilder.name("actual_registration.create").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic actualRegistrationUpdateTopic() {
        return TopicBuilder.name("actual_registration.update").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic actualRegistrationDeleteTopic() {
        return TopicBuilder.name("actual_registration.delete").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic actualRegistrationGetTopic() {
        return TopicBuilder.name("actual_registration.get").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic actualRegistrationErrorsTopic() {
        return TopicBuilder.name("actual_registration.errors").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic unknownErrorsTopic() {
        return TopicBuilder.name("unknown.errors").partitions(3).replicas(1).build();
    }
}