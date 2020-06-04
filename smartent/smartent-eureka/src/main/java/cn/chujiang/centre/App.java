package cn.chujiang.centre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class App {
	
	public static void main(String[] args) {
		new SpringApplication(App.class).run(args);
	}
}
