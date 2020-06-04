package cn.chujiang.simple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import cn.chujiang.simple.entity.UserEntity;
import cn.chujiang.simple.service.ISimpleService;
import cn.chujiang.simple.service.IUserService;


@RestController
public class SimpleController implements ISimpleService {

	@Autowired
	private IUserService iUserService;
	@Override
	public UserEntity getUserById(Integer uid) {
		if(uid <=0) throw new RuntimeException("id错误");
		return iUserService.getById(uid);
	}
	
	
}
