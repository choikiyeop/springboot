package com.example.springboot.global.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisUtil {
    private final RedisTemplate redisTemplate; // key, object용
    private final StringRedisTemplate stringRedisTemplate; // key, value용

    public void set(String key, String value, long timeoutMinutes) {
        stringRedisTemplate.opsForValue().set(key, value, Duration.ofMinutes(timeoutMinutes));
    }
    public void setObject(String key, Object object, long timeoutMinutes) {
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(object.getClass()));
        redisTemplate.opsForValue().set(key, object, Duration.ofMinutes(timeoutMinutes));
    }

    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public Object getObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    public void deleteObject(String key) {
        redisTemplate.delete(key);
    }

    public void increment(String key) {
        stringRedisTemplate.opsForValue().increment(key);
    }

    public Set<String> getKeysByPattern(String pattern) {
        return stringRedisTemplate.keys(pattern);
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
