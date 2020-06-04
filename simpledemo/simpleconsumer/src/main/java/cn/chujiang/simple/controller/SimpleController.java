package cn.chujiang.simple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.chujiang.simple.entity.UserEntity;
import cn.chujiang.simple.service.ISimpleService;
import feign.Param;

@RestController
public class SimpleController {
	@Autowired	
	private ISimpleService iSimpleService;
	
	@GetMapping("getUserById")
	public UserEntity  getUserById(@Param("uid")Integer uid) {
		return iSimpleService.getUserById(uid);
	}
		
}
