package com.ale.file.service.impl;

import cn.ucloud.ufile.api.object.ObjectApiBuilder;
import cn.ucloud.ufile.bean.PutObjectResultBean;
import cn.ucloud.ufile.exception.UfileClientException;
import cn.ucloud.ufile.exception.UfileServerException;
import cn.ucloud.ufile.http.OnProgressListener;
import com.ale.file.config.AliyunAutoConfiguration;
import com.ale.file.config.UCloudAutoConfiguration;
import com.ale.file.config.UCloudProperties;
import com.ale.file.service.FileStorageService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Slf4j
@Service
@ConditionalOnBean(UCloudAutoConfiguration.class)
public class UCloudStorageService implements FileStorageService {

    @Autowired
    private ObjectApiBuilder builder;
    @Autowired
    private UCloudProperties properties;

    @Override
    public String upload(byte[] data, String path) throws Exception {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public String upload(InputStream inputStream, String path) throws Exception {
        String fileUrl = "";
        try {
            String serverFileName = "";
            PutObjectResultBean response = builder.putObject(inputStream, inputStream.available(),"image/png")
                                                      .nameAs(serverFileName)
                                                      .toBucket(properties.getBucket())
                                                      /**
                                                       * 是否上传校验MD5, Default = true
                                                       */
                                                      //  .withVerifyMd5(false)
                                                      /**
                                                       * 指定progress callback的间隔, Default = 每秒回调
                                                       */
                                                      //  .withProgressConfig(ProgressConfig.callbackWithPercent(10))
                                                      /**
                                                       * 配置进度监听
                                                       */
                                                      .setOnProgressListener(new OnProgressListener() {
                                                          @Override
                                                          public void onProgress(long bytesWritten,
                                                                                 long contentLength) {

                                                          }
                                                      })
                                                      .execute();
            log.info("response:{}", JSONObject.toJSONString(response));

            if ("0".equals(String.valueOf(response.getRetCode()))) {
//                fileUrl = "http://申请的空间地址，如xxxx.cn-bj.ufileos.com/" + serverFileName;
                fileUrl = "http://goldfish.vn-sng.ufileos.com/" + serverFileName;
                log.info("云服务存储url:{}", fileUrl);
            }

        } catch (UfileClientException e) {
            e.printStackTrace();
        } catch (UfileServerException e) {
            e.printStackTrace();
        }
        return fileUrl;
    }


    @Override
    public String upload(File file, String path) throws Exception {
        return upload(new FileInputStream(file), path);
    }

    @Override
    public String getBaseUrl() {
        return "";
    }
}
