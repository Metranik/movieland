package com.art.movieland.configuration;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.art.movieland.dao", "com.art.movieland.service", "com.art.movieland.controller"})
@EnableScheduling
public class WebConfig {
    private static final ApplicationProperties APPLICATION_PROPERTIES = new ApplicationProperties();

    @Bean
    public PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yaml"));
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(APPLICATION_PROPERTIES.getDatasourceDriver());
        basicDataSource.setUrl(APPLICATION_PROPERTIES.getDatasourceUrl());
        basicDataSource.setUsername(APPLICATION_PROPERTIES.getDatasourceUser());
        basicDataSource.setPassword(APPLICATION_PROPERTIES.getDatasourcePassword());
        basicDataSource.setInitialSize(APPLICATION_PROPERTIES.getDatasourceInitialSize());
        basicDataSource.setMaxTotal(APPLICATION_PROPERTIES.getDatasourceMaxTotal());
        return basicDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
