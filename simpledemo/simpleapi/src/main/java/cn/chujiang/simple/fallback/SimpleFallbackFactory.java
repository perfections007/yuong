package cn.chujiang.simple.fallback;

import org.springframework.stereotype.Component;
import cn.chujiang.simple.entity.UserEntity;
import cn.chujiang.simple.service.ISimpleService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;	
/**
 * 使用@Component注册为组件
 * 
 * 配置熔断返回结果 FallbackFactory
 */
@Component
@Slf4j
public class SimpleFallbackFactory implements FallbackFactory<ISimpleService> {

	@Override
	public ISimpleService create(Throwable cause) {
		
		return new ISimpleService() {

			@Override
			public UserEntity getUserById(Integer uid) {
				log.error("FallbackFactory<ISimpleService> is invoke ~~~~~{}" ,cause.getMessage());
				return new UserEntity();
			}
				
		};
	}

}
