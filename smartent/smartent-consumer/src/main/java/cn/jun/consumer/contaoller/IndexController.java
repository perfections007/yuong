package cn.jun.consumer.contaoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.chujiang.provider.entity.UserEntity;
import cn.chujiang.service.IndexService;
/**
 * 消费者 实际对外暴露的接口控制器
 * @author zhejun
 *
 */
@RestController
public class IndexController {
	/**
	 * 自动注入 交由Feign代理实现
	 */
	@Autowired
	private IndexService indexServer;
	
	@GetMapping("/sayHello")
	public String sayHello(@RequestParam String name) {
		return indexServer.hello(name);
	}
	
	@GetMapping("/getUserById")
	public UserEntity getUserById(@RequestParam Integer uid) {
		return indexServer.getUserById(uid);
	}
	
	@GetMapping("/getAll")
	public List<UserEntity> getAll() {
		return indexServer.selectAll();
	}
}
