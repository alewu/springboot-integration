package com.ale.cors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
