package cn.chujiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SimpleProvierApp {

	public static void main(String[] args) {
		SpringApplication.run(SimpleProvierApp.class, args);
	}

}
