package com.ale.other;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SafeAPIController {

    /**
     * 解决请求重放的问题
     * 方法1：请求接口的参数中添加时间戳
     * 方法2：请求接口的参数中添加sign签名
     * @author alewu
     * @date 2020/5/15 11:23
     */
    @GetMapping()
    public ResponseEntity safe(String name, String pwd, @RequestParam("t1") Long timestamp) {
        StopWatch sw = new StopWatch();
        return ResponseEntity.ok().build();
    }
}
