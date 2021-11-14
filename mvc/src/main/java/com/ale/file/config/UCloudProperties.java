package com.ale.file.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
