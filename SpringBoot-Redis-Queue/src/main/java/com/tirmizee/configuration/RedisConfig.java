package com.tirmizee.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(standaloneConfiguration);
		lettuceConnectionFactory.setShareNativeConnection(true);
		lettuceConnectionFactory.setValidateConnection(true);
		return lettuceConnectionFactory;
	}
	
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
	    RedisTemplate<String, String> template = new RedisTemplate<>();
	    template.setConnectionFactory(redisConnectionFactory);
	    return template;
	}
	
	@Bean
	public ListOperations<String, String> listOperations(RedisTemplate<String, String> redisTemplate) {
		return redisTemplate.opsForList();
	}
	
}
