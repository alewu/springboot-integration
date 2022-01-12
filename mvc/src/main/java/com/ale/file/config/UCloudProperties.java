package com.ale.file.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * As of Spring Boot 2.2, Spring finds and registers @ConfigurationProperties classes via classpath scanning.
 * Therefore, there is no need to annotate such classes with @Component (and other meta-annotations like @Configuration),
 * or even use the @EnableConfigurationProperties.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "ucloud.ufile")
public class UCloudProperties {
    private String bucket;
    private String publicKey;
    private String privateKey;
    private String region;
    private String proxySuffix;
}
