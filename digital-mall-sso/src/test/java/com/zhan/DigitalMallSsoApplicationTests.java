package com.zhan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DigitalMallSsoApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;

	@SuppressWarnings("unchecked")
	@Test
	public void contextLoads() {
		System.out.println(redisTemplate.opsForValue().get("f6de9bf4-f5f7-4c86-8fe3-e0a614eef114").toString());
	}

}
