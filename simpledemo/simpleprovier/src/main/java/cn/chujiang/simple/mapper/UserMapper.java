package cn.chujiang.simple.mapper;

import cn.chujiang.simple.entity.UserEntity;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sp
 * @since 2020-06-03
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

}
