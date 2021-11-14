package com.ale.file.service.impl;

import com.ale.file.config.AliyunAutoConfiguration;
import com.ale.file.service.FileStorageService;
import com.aliyun.oss.OSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;

@Service
@ConditionalOnBean(AliyunAutoConfiguration.class)
public class AliyunCloudStorageService implements FileStorageService {

    @Autowired
    private OSS oss;

    @Override
    public String upload(byte[] data, String path) throws Exception {
        return "";
    }

    @Override
    public String upload(InputStream inputStream, String path) throws Exception {
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        try {
            oss.putObject("", path, inputStream);
        } finally {
            oss.shutdown();
        }
        return "";
    }

    @Override
    public String upload(File file, String path) throws Exception {
        return "";
    }

    @Override
    public String getBaseUrl() {
        return null;
    }
}
