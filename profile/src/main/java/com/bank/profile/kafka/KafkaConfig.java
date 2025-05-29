package com.bank.profile.kafka;

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
import org.springframework.kafka.config.TopicBuilder;
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
        return TopicBuilder.name("profile.create")
                .partitions(partitions)
                .replicas(replicas)
                .compact() // Для ключ-значение данных (ID профиля)
                .build();
    }

    @Bean
    public NewTopic profileUpdateTopic() {
        return TopicBuilder.name("profile.update")
                .partitions(partitions)
                .replicas(replicas)
                .compact()
                .build();
    }

    @Bean
    public NewTopic profileDeleteTopic() {
        return TopicBuilder.name("profile.delete")
                .partitions(1) // Одна партиция для упрощения
                .replicas(replicas)
                .build();
    }

    @Bean
    public NewTopic accountLinkTopic() {
        return TopicBuilder.name("account.link")
                .partitions(partitions)
                .replicas(replicas)
                .build();
    }

    @Bean
    public NewTopic accountCreatedTopic() {
        return TopicBuilder.name("account.created")
                .partitions(partitions)
                .replicas(replicas)
                .build();
    }

    // Консьюмер для JSON
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
    public ConcurrentKafkaListenerContainerFactory<String, AccountDetailsDto> accountFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AccountDetailsDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(accountConsumerFactory());
        return factory;
    }

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
    public ConcurrentKafkaListenerContainerFactory<String, ProfileDto> profileFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ProfileDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(profileConsumerFactory());
        return factory;
    }

    // Продюсер для JSON
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