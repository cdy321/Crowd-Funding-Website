package cn.cdy.crowd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate redisTemplate;



    @Test
    public void test01(){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("apple","red");
    }
    @Test
    public void test02(){
    }
}
