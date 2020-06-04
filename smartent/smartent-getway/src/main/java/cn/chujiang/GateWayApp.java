package cn.chujiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class GateWayApp {
  public static void main(String[] args) {
	  new SpringApplication(GateWayApp.class).run(args);
}
}
