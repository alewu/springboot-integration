package com.ale;

import cn.ucloud.ufile.api.bucket.BucketApiBuilder;
import cn.ucloud.ufile.api.bucket.BucketType;
import cn.ucloud.ufile.api.bucket.CreateBucketApi;
import cn.ucloud.ufile.api.bucket.DescribeBucketApi;
import cn.ucloud.ufile.bean.BucketDescribeResponse;
import cn.ucloud.ufile.bean.BucketResponse;
import com.ale.file.config.UCloudProperties;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author ale
 */
@SpringBootApplication
public class MvcSpringbootApplication {
    @Autowired
    private BucketApiBuilder builder;

    public static void main(String[] args) {
        SpringApplication.run(MvcSpringbootApplication.class, args);
    }

    @Bean
    CommandLineRunner initProxyClientRunner(final UCloudProperties properties) {
        return runArgs -> {
//            BucketResponse execute = builder.deleteBucket(properties.getBucket()).execute();
//            System.out.println(JSON.toJSONString(execute));

//            CreateBucketApi createBucketApi = builder.createBucket(properties.getBucket(), properties.getRegion(),
//                                                          BucketType.PRIVATE);
//            BucketResponse bucketResponse = createBucketApi.execute();
//            System.out.println(JSON.toJSONString(bucketResponse));
//
//            DescribeBucketApi describeBucketApi = builder.describeBucket();
//            describeBucketApi.whichBucket(properties.getBucket());
//            BucketDescribeResponse describeResponse = describeBucketApi.execute();
//            System.out.println(JSON.toJSONString(describeResponse));
//


        };
    }
}
