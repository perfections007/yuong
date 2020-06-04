package cn.chujiang.simple.service.impl;

import cn.chujiang.simple.entity.UserEntity;
import cn.chujiang.simple.mapper.UserMapper;
import cn.chujiang.simple.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sp
 * @since 2020-06-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

}
