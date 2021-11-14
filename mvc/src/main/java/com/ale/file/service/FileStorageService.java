package com.ale.file.service;

import com.ale.file.upload.entity.FileInfo;

import java.io.File;
import java.io.InputStream;

public interface FileStorageService {
    /**
     * 上传文件到云存储, 返回图片HTTP地址
     * @param data 字节数据
     * @param path 云存储文件路径
     * @return 文件地址
     * @throws Exception
     */
    String upload(byte[] data, String path) throws Exception;

    /**
     * 上传文件到云存储, 返回图片HTTP地址
     * @param inputStream 字节流
     * @param path 云存储文件路径
     * @return 文件地址
     * @throws Exception
     */
    String upload(InputStream inputStream, String path) throws Exception;

    /**
     * 上传文件到云存储, 返回图片HTTP地址
     * @param file 文件
     * @param path 云存储文件路径
     * @return 图片HTTP地址
     * @throws Exception
     */
    String upload(File file, String path) throws Exception;


//    String store(FileInfo fileInfo, );

    String getBaseUrl();



}
