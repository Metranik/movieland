package com.art.movieland.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.art.movieland.dao", "com.art.movieland.service"})
@EnableScheduling
public class RootConfig {
    @Bean
    public PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yaml"));
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    public DataSource dataSource(@Value("${datasource.driver}") String datasourceDriver,
                                 @Value("${datasource.url}") String datasourceUrl,
                                 @Value("${datasource.user}") String datasourceUser,
                                 @Value("${datasource.password}") String datasourcePassword,
                                 @Value("${datasource.dbcp2.initialSize}") int datasourceInitialSize,
                                 @Value("${datasource.dbcp2.maxTotal}") int datasourceMaxTotal) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(datasourceDriver);
        basicDataSource.setUrl(datasourceUrl);
        basicDataSource.setUsername(datasourceUser);
        basicDataSource.setPassword(datasourcePassword);
        basicDataSource.setInitialSize(datasourceInitialSize);
        basicDataSource.setMaxTotal(datasourceMaxTotal);
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
