package com.au.iress.toyrobot.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({ "com.au.iress.toyrobot.api" })
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
