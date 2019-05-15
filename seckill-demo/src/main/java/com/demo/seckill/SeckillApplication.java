package com.demo.seckill;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

//@SpringBootApplication(scanBasePackages = {"com.demo.seckill"})
@MapperScan("com.demo.seckill.dao")
@SpringBootApplication
public class SeckillApplication {
//    @PostConstruct
//    void started() {
//        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
//    }
    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication.class, args);
    }

}
