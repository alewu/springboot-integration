package com.ale.file.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@ConditionalOnExpression("'${file-storage.oss}'.contains('aliyun')")
@EnableConfigurationProperties(AliyunProperties.class)
public class AliyunAutoConfiguration extends BaseConfig {

    @Bean
    @ConditionalOnClass(name = "com.aliyun.oss.OSS")
    public OSS ossClient(AliyunProperties properties) {
        if (StringUtils.isEmpty(properties.getBucket())) {
            return null;
        }
        OSSClientBuilder clientBuilder = new OSSClientBuilder();
        return clientBuilder.build(properties.getEndpoint(), properties.getAccessKeyId(),
                                   properties.getAccessKeySecret());
    }

}
