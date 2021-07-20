package com.tirmizee.config;

import java.time.Duration;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import redis.clients.jedis.JedisPool;

@Configuration
public class RedisConfig {

	@Bean
	public JedisPool jedisPool() {
		GenericObjectPoolConfig<?> poolConfig = new GenericObjectPoolConfig<>();
		poolConfig.setMaxTotal(10);
		poolConfig.setMinIdle(5);
		poolConfig.setMaxIdle(10);
		return new JedisPool(poolConfig, "localhost");
	}
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory(JedisPool jedisPool) {
		
		RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration();
		standaloneConfiguration.setHostName("localhost");
		standaloneConfiguration.setPassword("foobared");
		standaloneConfiguration.setPort(6379);

		JedisClientConfiguration clientConfiguration = JedisClientConfiguration.builder()
			.connectTimeout(Duration.ofSeconds(3))
			.readTimeout(Duration.ofSeconds(3))
			.usePooling()
			.build();
		
		return new JedisConnectionFactory(standaloneConfiguration, clientConfiguration);
	}
	
	@Bean(name = "redisObjectTemplate")
	public RedisTemplate<String, Object> redisObjectTemplate(JedisConnectionFactory jedisConnectionFactory) {
		final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setEnableTransactionSupport(true);
		template.setKeySerializer(RedisSerializer.string());
	  	template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
	  	return template;
	}
	
	@Bean(name = "redisStringTemplate")
	public RedisTemplate<String, String> redisStringTemplate(JedisConnectionFactory jedisConnectionFactory) {
		RedisTemplate<String, String> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		template.setKeySerializer(RedisSerializer.string());
		template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	 }
	
}
