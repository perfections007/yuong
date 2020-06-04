package cn.chujiang.geteway.swagger.config;

import org.springframework.context.annotation.Configuration;

import com.didispace.swagger.butler.EnableSwaggerButler;
/**
 * swagger 聚合文档配置
 * zuul routers 映射具体服务的/v2/api-docs swagger 
 * @author zhejun
 *
 */
@Configuration
@EnableSwaggerButler
public class SwaggerConfig {

}
