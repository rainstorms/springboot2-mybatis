package rain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rain.utils.Redis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired private Redis redis;

    @Test
    public void redisTest() throws InterruptedException {
        // redis存储数据
        String key = "name";
        redis.setex(key, "rain", 60);
//        redis.set(key, "rain");
        // 获取数据
//        String value = redis.getStr(key);
//        System.out.println("获取缓存中key为" + key + "的值为：" + value);

        long ttl1 = redis.ttl(key);
        System.out.println("获取缓存中key为" + key + "值的有效为：" + ttl1);

        Thread.sleep(2000);
        long ttl = redis.ttl(key);
        System.out.println("获取缓存中key为" + key + "值的有效为：" + ttl);

        Thread.sleep(2000);
        redis.del(key);
        String value = redis.getStr(key);
        System.out.println("获取缓存中key为" + key + "的值为：" + value);

//        User user = User.builder().userId("11").area("时候").activeState("xx").build();
//        redis.set("user", user);
//        User newUser = (User) redis.get("user");
//        System.out.println("获取缓存中key为" + "user" + "的值为：" + newUser);

    }
}
