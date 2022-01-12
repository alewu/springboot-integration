package com.ale.file.upload;

import cn.ucloud.ufile.api.bucket.BucketApiBuilder;
import cn.ucloud.ufile.api.bucket.BucketType;
import cn.ucloud.ufile.api.bucket.CreateBucketApi;
import cn.ucloud.ufile.api.bucket.DescribeBucketApi;
import cn.ucloud.ufile.bean.BucketDescribeResponse;
import cn.ucloud.ufile.bean.BucketResponse;
import cn.ucloud.ufile.exception.UfileClientException;
import cn.ucloud.ufile.exception.UfileServerException;
import com.ale.file.config.UCloudProperties;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class FileUploadControllerTest {
    @Autowired
    private BucketApiBuilder builder;
    @Autowired
    private UCloudProperties properties;


    @Test
    void uploadFile() throws UfileServerException, UfileClientException {

        BucketResponse execute = builder.deleteBucket(properties.getBucket()).execute();
        System.out.println(JSON.toJSONString(execute));

        CreateBucketApi createBucketApi = builder.createBucket(properties.getBucket(), properties.getRegion(),
                BucketType.PRIVATE);
        BucketResponse bucketResponse = createBucketApi.execute();
        System.out.println(JSON.toJSONString(bucketResponse));

        DescribeBucketApi describeBucketApi = builder.describeBucket();
        describeBucketApi.whichBucket(properties.getBucket());
        BucketDescribeResponse describeResponse = describeBucketApi.execute();
        System.out.println(JSON.toJSONString(describeResponse));
    }
}