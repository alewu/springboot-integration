package com.ale.other;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class MimeController {


    @PostMapping("/test")
    public ResponseEntity test(@RequestBody String info) {
        // @RequestBody需要接的参数是一个string化的json
        //
        //@RequestBody，要读取的数据在请求体里，所以要发post请求，还要将Content-Type设置为application/json --经验证错误
        //
        //java的api
        System.out.println(info);
        return ResponseEntity.ok().build();
    }
}