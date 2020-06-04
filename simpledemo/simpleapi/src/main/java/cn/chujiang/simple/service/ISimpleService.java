package cn.chujiang.simple.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.chujiang.simple.entity.UserEntity;
import cn.chujiang.simple.fallback.SimpleFallbackFactory;

/**
 * 配置
 * @author zhejun
 *SIMPLE-PROVIDER
 */
@FeignClient(value = "simple-provider" ,fallbackFactory=SimpleFallbackFactory.class)
public interface ISimpleService {
	
		@GetMapping("/getUserById")
		public UserEntity  getUserById(@RequestParam("uid")Integer uid);
}
