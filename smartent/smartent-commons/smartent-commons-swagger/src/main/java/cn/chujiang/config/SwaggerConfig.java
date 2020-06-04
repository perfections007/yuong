package cn.chujiang.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.chujiang.properties.SwaggerProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfig {
	
    @Autowired
    private SwaggerProperties swaggerProperties;
    
    @Bean
    public Docket createRestApi() {
    	List<Parameter> parms=null;
    	ParameterBuilder tokenPar=null;
    	if(swaggerProperties.getDefaultParams() !=null) {
    		parms = new ArrayList<Parameter>();
    		List<Map<String, String>> maps = swaggerProperties.getDefaultParams();
    		
    		 for (Map<String, String> m : maps) {
    			 tokenPar = new ParameterBuilder();
    			 tokenPar.name(m.get("name")).description(m.get("desc")).
    				modelRef(new ModelRef(m.get("model"))).
    				parameterType(m.get("type")).required(Boolean.valueOf(m.get("required")));
    			 parms.add(tokenPar.build());
    		 }
    		
    	}
    		
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(parms);
    }
 
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDesc())
                .contact(new Contact(swaggerProperties.getName(), "", swaggerProperties.getEmail()))
                .version(swaggerProperties.getVersion())
                .build();
    }
}
