package cn.chujiang.properties;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

@ConfigurationProperties(prefix = "chujiang.swagger")
public class SwaggerProperties {
	private String name;
	private String version;
	private String email;
	private String desc;
	private String title;
	private String basePackage = "cn.chujiang";
	private List<Map<String, String>> defaultParams;

	public List<Map<String, String>> getDefaultParams() {
		return defaultParams;
	}

	public void setDefaultParams(List<Map<String, String>> defaultParams) {
		if(defaultParams==null || defaultParams.isEmpty())return ;
		this.defaultParams=defaultParams;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		if (StringUtils.isEmpty(basePackage))
			return;
		this.basePackage = basePackage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
