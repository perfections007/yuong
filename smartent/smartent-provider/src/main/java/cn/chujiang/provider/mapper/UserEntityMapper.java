package cn.chujiang.provider.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.chujiang.provider.entity.UserEntity;

@Mapper
public interface UserEntityMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);
    
    List<UserEntity> selectAll();
}