package com.ale.file.upload.entity;

import lombok.Data;

/**
 * The type File info.
 *
 * @author alewu
 */
@Data
public class FileInfo {
    /**
     * 文件原名称
     */
    private String originalName;
    /**
     * 文件新名称
     */
    private String newName;
    /**
     * 文件类型（image/jpg, image/png, video/mp4, xsl,doc等)
     */
    private String fileType;
    /**
     * 文件大小（kb）
     */
    private String fileSize;
    /**
     * 文件服务器存储路径
     */
    private String filePath;
    /**
     * 文件相对路径
     */
    private String relativePath;
    /**
     * 数据删除标记0=正常，1=文件已物理删除
     */
    private Boolean delFlag;

}
