package rain.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Redis {
    @Autowired private RedisTemplate redisTemplate;

    public String getStr(String key) {
        return (String) get(key);
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void del(String codeKey) {
        redisTemplate.delete(codeKey);
    }

    public void setex(String key, String value, int expire) {
        set(key, value);
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    public long ttl(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
}
