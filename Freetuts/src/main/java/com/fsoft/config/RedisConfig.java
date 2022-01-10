//package com.fsoft.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.session.data.redis.config.ConfigureRedisAction;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//
//@Configuration
//@EnableRedisHttpSession
//public class RedisConfig {
//
//	     @Bean
//	     public LettuceConnectionFactory redisConnectionFactory() {
//	         return new LettuceConnectionFactory();
//	     }
//	     @Bean
//	     public static ConfigureRedisAction configureRedisAction() {
//	         return ConfigureRedisAction.NO_OP;
//	     }
//	 
//}
