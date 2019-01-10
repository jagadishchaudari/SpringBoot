package com.jagadish.redis.springdataredis;

import com.jagadish.redis.springdataredis.Model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class SpringDataRedisApplication {

    //creating a Jedis Connection factory
    // Jedis is Redis Client implementation
    // Here is we are not specifying any host ip and port,
    //so it will try to connect to deafult redis server port 6379
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    // Here we are trying to define the Redis template using the Jedis connection factory
    // This can be used for querying data with a custom repository(UserRepository in this case).
    @Bean
    RedisTemplate<String, User> redisTemplate() {
        RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

	public static void main(String[] args) {

		SpringApplication.run(SpringDataRedisApplication.class, args);
	}

}

