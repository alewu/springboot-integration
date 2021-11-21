package com.ale.file.service;

import com.ale.file.upload.entity.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type File upload service.
 *
 * @author alewu
 */
@Service
public class FileUploadService {

    @Resource
//    @Qualifier("uCloudStorageService")
    private FileStorageService fileStorageService;

    /**
     * Upload map.
     *
     * @param multipartFile the multipart files
     * @return the map
     */
    public List<FileInfo> upload(MultipartFile multipartFile) {
        List<FileInfo> fileInfos = new ArrayList<>();
        // 文件存放路径
        String filePath = genStorageFilePath();
        // 文件名称
        String originFileName = multipartFile.getOriginalFilename();
        // 保存文件到磁盘
        try {
            InputStream inputStream = multipartFile.getInputStream();
            fileStorageService.upload(inputStream, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileInfos;
    }

    /**
     * Upload map.
     *
     * @param multipartFiles the multipart files
     * @return the map
     */
    public List<FileInfo> upload(List<MultipartFile> multipartFiles) {
        List<FileInfo> fileInfos = new ArrayList<>();
        // 文件存放路径
        String filePath = genStorageFilePath();
        // 文件名称
        Map<String, String> originNameToNewName = getOriginFileToNewFile(multipartFiles);
        // 保存文件到磁盘
        saveFileToDisc(filePath, originNameToNewName, multipartFiles);
        return fileInfos;
    }


    private Map<String, String> getOriginFileToNewFile(List<MultipartFile> multipartFiles) {
        Map<String, String> originNameToNewName = new HashMap<>(multipartFiles.size());
        // 文件名称
        for (MultipartFile multipartFile : multipartFiles) {
            // 获取文件原始的名称
            String originFileName = multipartFile.getOriginalFilename();
            // 生成新的文件名称
            String newFileName = genNewFileName(originFileName);
            originNameToNewName.put(originFileName, newFileName);
        }
        return originNameToNewName;
    }

    private String genStorageFilePath() {
        // 自定义文件路径生成规则， 路径存在是否覆盖？,配置文件配置
        //获取项目运行的绝对路径
        String filePath = System.getProperty("user.dir");
        String newFilePath = filePath + "\\demo-upload\\src\\main\\resources\\static\\images\\";
        File file = new File(newFilePath);
        boolean mkdirs = false;
        if (!file.exists()) {
            mkdirs = file.mkdirs();
        }
        return null;
    }

    private String genNewFileName(String originFileName) {
        // 自定义文件名生成规则
        return LocalDateTime.now() + originFileName;
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
