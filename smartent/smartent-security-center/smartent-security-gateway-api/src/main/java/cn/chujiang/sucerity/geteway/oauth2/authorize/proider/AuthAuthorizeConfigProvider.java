package cn.chujiang.sucerity.geteway.oauth2.authorize.proider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import cn.chujiang.security.oauth.model.properties.PermitUrlProperties;
import cn.chujiang.sucerity.geteway.oauth2.authorize.AuthorizeConfigProvider;

/**
 * 配置permitAll的路径
 * @author zhejun
 *
 */
@Component
@Order(Integer.MAX_VALUE - 1)
@EnableConfigurationProperties(PermitUrlProperties.class)
public class AuthAuthorizeConfigProvider implements AuthorizeConfigProvider {

	@Autowired(required = false)
	private PermitUrlProperties permitUrlProperties;

	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {

		// 免token登录设置
		config.antMatchers(permitUrlProperties.getOauth_urls()).permitAll();
		//前后分离核心配置
		config.antMatchers(HttpMethod.OPTIONS).permitAll();
		return true;
	}

}