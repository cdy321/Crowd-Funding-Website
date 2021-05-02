package cn.cdy.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaMainType {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMainType.class,args);
    }
}
