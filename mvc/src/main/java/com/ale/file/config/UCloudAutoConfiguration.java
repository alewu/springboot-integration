package com.ale.file.config;

import cn.ucloud.ufile.UfileClient;
import cn.ucloud.ufile.api.bucket.BucketApiBuilder;
import cn.ucloud.ufile.api.object.ObjectApiBuilder;
import cn.ucloud.ufile.api.object.ObjectConfig;
import cn.ucloud.ufile.auth.BucketAuthorization;
import cn.ucloud.ufile.auth.ObjectAuthorization;
import cn.ucloud.ufile.auth.UfileBucketLocalAuthorization;
import cn.ucloud.ufile.auth.UfileObjectLocalAuthorization;
import cn.ucloud.ufile.http.HttpClient;
import cn.ucloud.ufile.util.JLog;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@ConditionalOnExpression("'${file-storage.oss}'.contains('ucloud')")
@Configuration
@EnableConfigurationProperties(UCloudProperties.class)
public class UCloudAutoConfiguration extends BaseConfig {

    @Bean
    public ObjectApiBuilder getObjectApiBuilder(UCloudProperties properties) {
        JLog.SHOW_TEST = true;
        JLog.SHOW_DEBUG = true;
        UfileClient.configure(new UfileClient.Config(
                new HttpClient.Config(10, 5, TimeUnit.MINUTES)
                        .setTimeout(10 * 1000, 30 * 1000, 30 * 1000)
                        .setExecutorService(Executors.newSingleThreadExecutor())
                //                        .addInterceptor(okhttp3拦截器)
                //                        .addNetInterceptor(okhttp3网络拦截器)
        ));
        ObjectAuthorization OBJECT_AUTHORIZER = new UfileObjectLocalAuthorization(properties.getPublicKey(),
                                                                                  properties.getPrivateKey());
        ObjectConfig objectConfig = new ObjectConfig(properties.getRegion(), properties.getProxySuffix());
        return UfileClient.object(OBJECT_AUTHORIZER, objectConfig);

    }

    @Bean
    public BucketApiBuilder getBucketApiBuilder(UCloudProperties properties) {
        BucketAuthorization BUCKET_AUTHORIZER = new UfileBucketLocalAuthorization(
                properties.getPublicKey(), properties.getPrivateKey());
        return UfileClient.bucket(BUCKET_AUTHORIZER);

    }


}
