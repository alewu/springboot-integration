package com.ale.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;
import java.util.List;
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
    @PostMapping("")
    public ResponseEntity<List<Integer>> validateList(@Size(min = 1, message = "ids can be empty!") @RequestBody List<Integer> ids) {

        return ResponseEntity.ok(ids.stream().map(integer -> integer * 2).collect(Collectors.toList()));
    }
}
