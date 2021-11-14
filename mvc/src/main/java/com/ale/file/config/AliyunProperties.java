package com.ale.file.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunProperties {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucket;
}
