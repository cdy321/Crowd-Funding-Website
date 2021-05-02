package cn.cdy.crowd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("cn.cdy.crowd.dao")
@SpringBootApplication
public class MysqlMainType {
    public static void main(String[] args) {
        SpringApplication.run(MysqlMainType.class,args);
    }
}
