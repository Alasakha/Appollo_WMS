package com.mgkj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author : ssy
 * @date: 2023-12-15
 * @Description:
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@EnableScheduling
public class YiFeiWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(YiFeiWebApplication.class);
    }
}
