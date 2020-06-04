package cn.chujiang.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.chujiang.provider.entity.UserEntity;

/**
 * @FeignClient该接口交由Feign代理  开启Feign
 * 
 * Feign接口调用 底层🈶由ribbon代理实现
 * 
 * 
 * value = "smartent-provider"  声明生产者的服务名称
 * @author zhejun
 *
 */
@FeignClient(value = "smartent-provider")
//@RequestMapping
public interface IndexService {

	/**
	 * 生产者也就是实际业务提供着的请求路径在这里配置
	 * value = "/hello"请求路径
	 * method = RequestMethod.GET 请求方式
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	public String hello(@RequestParam(value = "name") String name);
	@RequestMapping(value = "/getUserById",method = RequestMethod.GET)
	public UserEntity getUserById(@RequestParam(value = "uid") Integer uid);
	
	@RequestMapping(value = "/selectAll",method = RequestMethod.GET)
	public List<UserEntity> selectAll();
}
