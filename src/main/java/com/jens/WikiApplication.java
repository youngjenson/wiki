package com.jens;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
public class WikiApplication {

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(WikiApplication.class, args);
        Environment env = app.getEnvironment();
        log.info("启动成功");
        log.info("地址:\thttp://127.0.0.1:{}",env.getProperty("server.port"));
    }

}
