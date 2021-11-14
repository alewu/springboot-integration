package com.ale.file.upload;

import com.ale.common.BusinessException;
import com.ale.common.BusinessExceptionEnum;
import com.ale.file.service.FileUploadService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author alewu
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/files")
@Validated
public class FileUploadController {
    private final FileUploadService fileUploadService;


    @PostMapping(value = "/upload/batch", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public String batchUploadFile(@Size(min = 1, message = "xxx") @RequestParam("files") List<MultipartFile> multipartFiles) {
        if (CollectionUtils.isEmpty(multipartFiles)) {
            return "upload file is empty!";
        }
        // 文件大小
        boolean existedZeroKbOfFile = multipartFiles.stream().anyMatch(multipartFile -> multipartFile.getSize() <= 0);
        if (existedZeroKbOfFile) {
            throw new BusinessException(BusinessExceptionEnum.FILE_UPLOAD_EXCEPTION);
            //            return "上传的文件大小需要大于0kb";
        }

        // 文件内容
        processContentType(multipartFiles);

        fileUploadService.upload(multipartFiles);


        return "上传失败";
    }

    private void processContentType(List<MultipartFile> multipartFiles) {
        // file.getContentType() image/png
    }


    @PostMapping(value = "/upload", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public String uploadFile(@Size(min = 1, message = "xxx")  @RequestParam("file") MultipartFile multipartFile) {


        return "上传ok";
    }


}
