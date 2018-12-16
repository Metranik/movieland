package com.art.movieland.configuration;

import com.art.movieland.controller.interceptor.LoggerInterceptor;
import com.art.movieland.controller.interceptor.ProtectedByInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.art.movieland.controller"})
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public LoggerInterceptor loggerInterceptor() {
        return new LoggerInterceptor();
    }

    @Bean
    public ProtectedByInterceptor protectedByInterceptor() {
        return new ProtectedByInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggerInterceptor());
        registry.addInterceptor(protectedByInterceptor());
    }
}
