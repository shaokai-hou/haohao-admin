package com.haohao.admin.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.haohao.admin.module.system.entity.SysParamEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        // key序列化
        template.setKeySerializer(new StringRedisSerializer());
        // value序列化
        template.setValueSerializer(jsonRedisSerializer);
        return template;
    }

    @Bean
    public RedisTemplate<String, Integer> integerRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Integer> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        // key序列化
        template.setKeySerializer(new StringRedisSerializer());
        // value序列化
        template.setValueSerializer(jsonRedisSerializer);
        return template;
    }

    @Bean
    public RedisTemplate<String, SysParamEntity> paramRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, SysParamEntity> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<SysParamEntity> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(SysParamEntity.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        jsonRedisSerializer.setObjectMapper(objectMapper);
        // key序列化
        template.setKeySerializer(new StringRedisSerializer());
        // value序列化
        template.setValueSerializer(jsonRedisSerializer);
        return template;
    }
}
