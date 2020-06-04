package cn.chujiang.sucerity.geteway.oauth2.authorize.proider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import cn.chujiang.sucerity.geteway.oauth2.authorize.AuthorizeConfigManager;
import cn.chujiang.sucerity.geteway.oauth2.authorize.AuthorizeConfigProvider;

/**
 * 
 * @author zhejun
 *
 */
@Component
public class OpenAuthorizeConfigManager implements AuthorizeConfigManager {


	@Autowired
	private List<AuthorizeConfigProvider> authorizeConfigProviders;

	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		 
		//设置访问
		for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {
			
			authorizeConfigProvider.config(config) ;
		}
		
		//token正确登录
		//除了自己配置的都需要认证
		config.anyRequest().authenticated() ;
		
//		放开则根据client可访问列表进行授权访问，真的clientid的api权限
//		config
//		.anyRequest()
//			.access("@rbacService.hasPermission(request, authentication)");
		
		
	}

}
