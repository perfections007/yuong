package cn.chujiang.security.server.oauth2.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.chujiang.security.commons.model.user.LoginAppUser;
import cn.chujiang.security.commons.model.user.SysRole;
import cn.chujiang.security.server.oauth2.feign.UserClient;
import lombok.extern.slf4j.Slf4j;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserClient userClient;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//      后续考虑集成spring socail,支持多种类型登录
    	LoginAppUser loginAppUser = new LoginAppUser();
    	loginAppUser.setId(2L);
		loginAppUser.setNickname("欧文");
		loginAppUser.setUsername("owen");
		loginAppUser.setPassword("$2a$10$i3F515wEDiB4Gvj9ym9Prui0dasRttEUQ9ink4Wpgb4zEDCAlV8zO");
		loginAppUser.setHeadImgUrl("http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg");
		loginAppUser.setPhone("18579068166");
		loginAppUser.setEnabled(true);
		loginAppUser.setType("APP");
		loginAppUser.setCreateTime(new Date());
		loginAppUser.setUpdateTime(new Date());
		
		Set<SysRole> sysRoles = new HashSet<SysRole>();
		SysRole role = new SysRole();
		role.setCode("USER");
		role.setCreateTime(new Date());
		role.setId(3l);
		role.setName("游客");
		role.setUserId(2L);
		sysRoles.add(role);
		loginAppUser.setSysRoles(sysRoles);
		Set<String> permissions = new HashSet<String>();
		permissions.add("/hello");
		permissions.add("/selectAll");
		loginAppUser.setPermissions(permissions);
		
        if (loginAppUser == null) {
            throw new AuthenticationCredentialsNotFoundException("用户不存在");
        } else if (!loginAppUser.isEnabled()) {
            throw new DisabledException("用户已作废");
        }

        return loginAppUser;
    }


     
}
