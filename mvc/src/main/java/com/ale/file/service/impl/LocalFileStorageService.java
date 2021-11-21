package com.ale.file.service.impl;

import com.ale.file.service.FileStorageService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
@ConditionalOnExpression("'${file-storage.oss}'.contains('local')")
public class LocalFileStorageService implements FileStorageService {



    @Override
    public String upload(byte[] data, String path) throws Exception {
        return "";

    }

    @Override
    public String upload(InputStream inputStream, String path) throws Exception {
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

    private void saveFileToDisc(String filePath, Map<String, String> originNameToNewName,
                                List<MultipartFile> multipartFiles) {
        // 1、若有一个文件失败，其他是否删除？ 2、多线程写入？
        for (MultipartFile multipartFile : multipartFiles) {
            try (FileOutputStream fileOutputStream =
                         new FileOutputStream(filePath + originNameToNewName.getOrDefault(multipartFile.getOriginalFilename(), "default"));) {
                fileOutputStream.write(multipartFile.getBytes());
                fileOutputStream.flush();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }

}
