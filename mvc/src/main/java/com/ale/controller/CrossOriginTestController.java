package com.ale.controller;

import com.ale.common.BaseResponse;
import com.ale.entity.Dept;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author alewu
 */
@Slf4j
@RequestMapping("/cross-origin")
@RestController
@AllArgsConstructor
public class CrossOriginTestController {

    @GetMapping("/hello")
    @CrossOrigin
    public String hello() {
       return "test-cross-origin";
    }
}
