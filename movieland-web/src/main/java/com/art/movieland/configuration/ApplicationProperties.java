package com.art.movieland.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
final class ApplicationProperties {
    @Value("${datasource.driver}")
    private String datasourceDriver;
    @Value("${datasource.url}")
    private String datasourceUrl;
    @Value("${datasource.user}")
    private String datasourceUser;
    @Value("${datasource.password}")
    private String datasourcePassword;
    @Value("${datasource.dbcp2.initialSize}")
    private int datasourceInitialSize;
    @Value("${datasource.dbcp2.maxTotal}")
    private int datasourceMaxTotal;
}
