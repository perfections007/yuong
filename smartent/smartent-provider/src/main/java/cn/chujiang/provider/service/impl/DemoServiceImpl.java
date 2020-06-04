package cn.chujiang.provider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.chujiang.provider.entity.UserEntity;
import cn.chujiang.provider.mapper.UserEntityMapper;
import cn.chujiang.provider.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {
	@Autowired
	private UserEntityMapper userEntityMapper;

	@Cacheable(cacheNames="user",key="#p0")
	public UserEntity getUserById(Integer uid) {
		return userEntityMapper.selectByPrimaryKey(uid);
	}

	@Cacheable(cacheNames="users")
	public List<UserEntity> selectAll() {
		return userEntityMapper.selectAll();
	}

}
