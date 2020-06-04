package cn.chujiang.provider.service;

import java.util.List;

import cn.chujiang.provider.entity.UserEntity;

public interface DemoService {
   
	public  UserEntity  getUserById(Integer uid);

	public List<UserEntity> selectAll();
}
