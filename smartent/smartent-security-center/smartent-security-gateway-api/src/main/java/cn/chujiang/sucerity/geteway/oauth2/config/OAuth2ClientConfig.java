package cn.chujiang.sucerity.geteway.oauth2.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.chujiang.security.oauth.model.properties.PermitUrlProperties;
import cn.chujiang.sucerity.geteway.oauth2.authorize.AuthorizeConfigManager;
import lombok.extern.slf4j.Slf4j;


/**
 * OAuth2 过滤配置
 * @author zhejun
 *
 */
@Component
@Configuration
@EnableResourceServer
// 开启spring security 注解
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableConfigurationProperties(PermitUrlProperties.class)
@Slf4j
public class OAuth2ClientConfig extends ResourceServerConfigurerAdapter{

		@Resource
		private ObjectMapper objectMapper; // springmvc启动时自动装配json处理类

		@Autowired(required = false)
		private TokenStore redisTokenStore;

		@Autowired(required = false)
		private JwtTokenStore jwtTokenStore;
	//	@Autowired(required = false)
	//	private JwtAccessTokenConverter jwtAccessTokenConverter;

		@Autowired
		private AuthenticationEntryPoint authenticationEntryPoint;
		//@Autowired
		//private AuthenticationFailureHandler authenticationFailureHandler;

		@Autowired
		private AuthorizeConfigManager authorizeConfigManager;

		//@Autowired
		//private OAuth2WebSecurityExpressionHandler expressionHandler;
		@Autowired
		private AccessDeniedHandler accessDeniedHandler;

		@Autowired
		private PermitUrlProperties permitUrlProperties;

		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers(permitUrlProperties.getHttp_urls());
		}

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			log.debug("jwtTokenStore~~~~~~~"+jwtTokenStore);
			log.debug("redisTokenStore~~~~~~~"+redisTokenStore);
			if (jwtTokenStore != null) {
				resources.tokenStore(jwtTokenStore);
			} else if (redisTokenStore != null) {
				resources.tokenStore(redisTokenStore);
			}
			resources.stateless(true);

			resources.authenticationEntryPoint(authenticationEntryPoint);

			resources.expressionHandler(new OAuth2WebSecurityExpressionHandler());
			resources.accessDeniedHandler(accessDeniedHandler);

		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.csrf().disable();
			http.headers().frameOptions().disable();
			authorizeConfigManager.config(http.authorizeRequests());

		}
}
