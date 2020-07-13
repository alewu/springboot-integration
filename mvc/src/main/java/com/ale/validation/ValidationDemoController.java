package com.ale.validation;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author alewu
 * @date 2020/6/28
 */
@RestController
@RequestMapping("/validation")
@Validated
public class ValidationDemoController {
    /**
     * @author alewu
     * @date 2020/6/28 18:41
     */
    @PostMapping("/list")
    public ResponseEntity<List<Integer>> validateList(@Size(min = 1, message = "ids can be empty!") @RequestBody List<Integer> ids) {

        return ResponseEntity.ok(ids.stream().map(integer -> integer * 2).collect(Collectors.toList()));
    }


    /**
     * @author alewu
     * @date 2020/6/28 18:41
     */
    @GetMapping("/date")
    public ResponseEntity<LocalDateTime> validateDate(@Future(message = "发送时间必须大于当前时间!") @DateTimeFormat(pattern =
            "yyyy-MM-dd HH:mm:ss") LocalDateTime sendTime) {

        return ResponseEntity.ok(sendTime);
    }

    /**
     * @author alewu
     * @date 2020/6/28 18:41
     */
    @PostMapping(value = "/file", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public ResponseEntity<String> validateFile(@RequestParam("file") @Image MultipartFile file) {
        String originalFilename = Optional.ofNullable(file.getOriginalFilename()).orElse("");
        return ResponseEntity.ok(originalFilename);
    }
}
