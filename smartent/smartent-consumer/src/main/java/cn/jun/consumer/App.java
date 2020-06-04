package cn.jun.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "cn.chujiang")
@EnableEurekaClient
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
