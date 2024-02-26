package com.jens.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                //设置允许的headers属性
                .allowedHeaders(CorsConfiguration.ALL)
                //设置允许的请求方式
                .allowedMethods(CorsConfiguration.ALL)
                //是否允许cookie
                .allowCredentials(true)
                //跨域允许时间
                .maxAge(3600); //1小时内不需要预检
    }
}
