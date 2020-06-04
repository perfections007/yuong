package cn.chujiang.security.oauth.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Resource
	private RedisTemplate< String, Object> redisTemplate ;

	@GetMapping("/test111")
	public String hello() {
		return "hello";
	}

}
