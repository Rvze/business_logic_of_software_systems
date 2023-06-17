package com.mpanchuk.app.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mpanchuk.app.domain.StashPair;
import com.mpanchuk.app.domain.response.OrderResponse;
import com.mpanchuk.app.model.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;
import java.util.UUID;


@Configuration
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String hostname;

    @Value("${spring.data.redis.port}")
    private Integer port;

    @Bean
    LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(hostname, port));
    }

    @Bean
    RedisTemplate<String, List<StashPair<Item, Integer>>> orderTemplate() {
        final RedisTemplate<String, List<StashPair<Item, Integer>>> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(RedisSerializer.string());
        final var jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        final var objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL,  JsonAutoDetect.Visibility.ANY);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}