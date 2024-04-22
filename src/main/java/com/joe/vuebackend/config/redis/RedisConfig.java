package com.joe.vuebackend.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // (1)配置連接工廠
        redisTemplate.setConnectionFactory(factory);

        // (2)JSON序列化配置
        // 為保證jackson格式的數據可以反序列化，必須將轉換的對象類型也儲存到Json中，否則在反序列化時，系統因為不清楚原本類型會報錯。
        ObjectMapper om = new ObjectMapper();
        //指定要序列化的域，field、get、set以及修飾範圍，ANY都是包含private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        /*
            序列化時在JSON上包含類型的資料
            LaissezFaireSubTypeValidator.instance 允許序列化和反序列化時使用較寬鬆的規則
            ObjectMapper.DefaultTyping.NON_FINAL 示只將非 final 類的類型資訊包含在序列化中。
            JsonTypeInfo.As.PROPERTY 在 JSON 中類型資訊的表示方式，它將以屬性的形式添加到 JSON 物件中。
         */
        om.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );
        // 反序列化時，如果遇到未知屬性，是否拋出異常，false不拋異常，忽略該未知屬性
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // (3)使用jackson2JsonRedisSerializer來序列化和反序列化redis的value值
        // 將定義好的「JSON序列化配置」放入，並指定使用Object類型，代表可以放入任何類型
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(om, Object.class);

        // 設置key序列化模式【以String方式】
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 值採用Json序列化 【自定義設置後的jackson2JsonRedisSerializer】
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        //設置hash key和value序列化模式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        //初始化redisTemplate
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
