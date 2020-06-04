package cn.chujiang.provider.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.chujiang.provider.entity.UserEntity;
import cn.chujiang.provider.model.LoginAppUser;
import cn.chujiang.provider.model.SysRole;
import cn.chujiang.provider.service.DemoService;
import cn.chujiang.service.IndexService;

/**
 * 生产者controller
 * 实现了Feign的接口  这样消费者直接通过Feign代理请求该控制器
 * implements IndexService
 * @author zhejun
 *
 */
@RestController
//@RefreshScope  Springconfig
public class IndexController   implements IndexService{
	@GetMapping(value = "/users-anon/login", params = "username")
    public LoginAppUser findByUsername(String username) {
		LoginAppUser user = new LoginAppUser();
		user.setId(2L);
		user.setNickname("欧文");
		user.setUsername("owen");
		user.setPassword("$2a$10$i3F515wEDiB4Gvj9ym9Prui0dasRttEUQ9ink4Wpgb4zEDCAlV8zO");
		user.setHeadImgUrl("http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg");
		user.setPhone("18579068166");
		user.setEnabled(true);
		user.setType("APP");
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		
		Set<SysRole> sysRoles = new HashSet<SysRole>();
		SysRole role = new SysRole();
		role.setCode("USER");
		role.setCreateTime(new Date());
		role.setId(3l);
		role.setName("游客");
		role.setUserId(2L);
		sysRoles.add(role);
		user.setSysRoles(sysRoles);
		Set<String> permissions = new HashSet<String>();
		permissions.add("/hello");
		permissions.add("/selectAll");
		user.setPermissions(permissions);
		
		return user;
		
    }
//	@Value("${name}")
//	private String name;
//	@Value("${age}")
//	private String age;
//	@GetMapping("/test")
//	public String test() {
//		return name+"\t"+age;
//	}
	@Autowired
	private DemoService demoServceic;
	/**
	 * 无需使用request注解声明请求路径 该路径已经在接口中声明
	 */
	@Override
	public String hello(String name) {
		return "你好"+name;
	}
	@Override
	public UserEntity getUserById(Integer uid) {
		return demoServceic.getUserById(uid);
	}

	@Override
	public List<UserEntity> selectAll() {
		// TODO Auto-generated method stub
		return demoServceic.selectAll();
	}

}
