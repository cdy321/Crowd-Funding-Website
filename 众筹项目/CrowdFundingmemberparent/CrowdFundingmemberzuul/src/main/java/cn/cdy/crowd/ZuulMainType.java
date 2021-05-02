package cn.cdy.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ZuulMainType {
    public static void main(String[] args) {
        SpringApplication.run(ZuulMainType.class,args);
    }
}
