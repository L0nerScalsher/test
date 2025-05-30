package com.bank.profile.kafka;

import com.bank.profile.ExceptionHandling.GlobalExceptionHandler;
import com.bank.profile.dto.AccountDetailsDto;
import com.bank.profile.dto.ProfileDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.topic.partitions:3}")
    private int partitions;

    @Value("${spring.kafka.topic.replicas:1}")
    private short replicas;

    // Топики
    @Bean
    public NewTopic profileCreateTopic() {
        return new NewTopic("profile.create", partitions, replicas);
    }

    @Bean
    public NewTopic profileUpdateTopic() {
        return new NewTopic("profile.update", partitions, replicas);
    }

    @Bean
    public NewTopic profileDeleteTopic() {
        return new NewTopic("profile.delete", 1, replicas);
    }

    @Bean
    public NewTopic profileGetTopic() {
        return new NewTopic("profile.get", partitions, replicas);
    }

    @Bean
    public NewTopic profileErrorsTopic() {
        return new NewTopic("profile.errors", 1, replicas);
    }

    @Bean
    public NewTopic accountCreatedTopic() {
        return new NewTopic("account.created", partitions, replicas);
    }

    // Консьюмер для ProfileDto
    @Bean
    public ConsumerFactory<String, ProfileDto> profileConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.bank.profile.dto");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, ProfileDto.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProfileDto> profileFactory(GlobalExceptionHandler errorHandler) {
        ConcurrentKafkaListenerContainerFactory<String, ProfileDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(profileConsumerFactory());
        factory.setCommonErrorHandler(errorHandler);
        return factory;
    }

    // Консьюмер для AccountDetailsDto
    @Bean
    public ConsumerFactory<String, AccountDetailsDto> accountConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.bank.profile.dto");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, AccountDetailsDto.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AccountDetailsDto> accountFactory(GlobalExceptionHandler errorHandler) {
        ConcurrentKafkaListenerContainerFactory<String, AccountDetailsDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(accountConsumerFactory());
        factory.setCommonErrorHandler(errorHandler);
        return factory;
    }

    // Продюсер
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}