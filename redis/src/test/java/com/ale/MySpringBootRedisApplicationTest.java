package com.ale;

import com.ale.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class MySpringBootRedisApplicationTest {
    @Autowired
    private RedisTemplate<String, String> strRedisTemplate;
    @Autowired
    private RedisTemplate<String, Object> serializableRedisTemplate;

    @Test
    public void testString() {
        strRedisTemplate.opsForValue().set("strKey", "zwqh");
        System.out.println(strRedisTemplate.opsForValue().get("strKey"));
    }

    @Test
    public void testSerializable() {
        UserEntity user=new UserEntity();
        user.setId(1L);
        user.setUserName("jack");
        user.setUserSex("man");
        serializableRedisTemplate.opsForValue().set("user", user);
        UserEntity user2 = (UserEntity) serializableRedisTemplate.opsForValue().get("user");
        System.out.println("user:"+user2.getId()+","+user2.getUserName()+","+user2.getUserSex());
    }

    @Test
    public void testIncr(){
        String key = "anonymous_user_id:a48b313f8f5911eaa342-525400236ced:qr_code_switch_count";
        System.out.println(strRedisTemplate.opsForValue().increment(key));
        System.out.println(strRedisTemplate.opsForValue().get(key));
        System.out.println(strRedisTemplate.getExpire(key));
        System.out.println(strRedisTemplate.expire(key, 10, TimeUnit.MILLISECONDS));
    }



    @Test
    public void testSet(){
        String key = "link_relation_id:1:user_switch_list";
        System.out.println(strRedisTemplate.opsForSet().members(key));
        System.out.println(strRedisTemplate.getExpire(key));
        //        System.out.println(strRedisTemplate.expire(key, 1, TimeUnit.MILLISECONDS));
    }

    @Test
    public void testHash(){
        String key = "anonymous_user_id:a48b313f8f5911eaa342-525400236ced:qr_code_switch_count";
        strRedisTemplate.opsForHash().put("hidden_qr_code", "a48b313f8f5911eaa342-525400236ced", "1111111111111111");
        System.out.println(strRedisTemplate.opsForHash().get("hidden_qr_code", "a48b313f8f5911eaa342-525400236ced"));
        System.out.println(strRedisTemplate.getExpire(key));
        System.out.println(strRedisTemplate.expire(key, 10, TimeUnit.MILLISECONDS));
    }

}