package com.ale.validation;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Validation demo controller.
 *
 * @author alewu
 * @date 2020 /6/28
 */
@RestController
@RequestMapping("/validation")
@Validated
public class ValidationDemoController {

    /**
     * Validate entity response entity.
     *
     * @param book the book
     * @return the response entity
     * @author alewu
     * @date 2020 /7/19
     */
    @PostMapping("/entity")
    public ResponseEntity<Book> validateEntity(@Valid @RequestBody Book book) {

        return ResponseEntity.ok(book);
    }

    /**
     * Validate list response entity.
     *
     * @param ids the ids
     * @return the response entity
     * @author alewu
     * @date 2020 /6/28 18:41
     */
    @PostMapping("/list")
    public ResponseEntity<List<Integer>> validateList(@Size(min = 1, message = "ids can be empty!") @RequestBody List<Integer> ids) {

        return ResponseEntity.ok(ids.stream().map(integer -> integer * 2).collect(Collectors.toList()));
    }


    /**
     * Validate date response entity.
     *
     * @param sendTime the send time
     * @return the response entity
     * @author alewu
     * @date 2020 /6/28 18:41
     */
    @GetMapping("/date")
    public ResponseEntity<LocalDateTime> validateDate(@Future(message = "发送时间必须大于当前时间!") @DateTimeFormat(pattern =
            "yyyy-MM-dd HH:mm:ss") LocalDateTime sendTime) {

        return ResponseEntity.ok(sendTime);
    }

    /**
     * Validate file response entity.
     *
     * @param file the file
     * @return the response entity
     * @author alewu
     * @date 2020 /6/28 18:41
     */
    @PostMapping(value = "/file", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public ResponseEntity<String> validateFile(@RequestParam("file") @Image MultipartFile file) {
        String originalFilename = Optional.ofNullable(file.getOriginalFilename()).orElse("");
        return ResponseEntity.ok(originalFilename);
    }
}
