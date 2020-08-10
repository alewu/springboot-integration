package com.ale.swagger;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alewu
 * @date 2020-04-27 14:28
 */
@Slf4j
@RequestMapping("/user")
@RestController
@AllArgsConstructor
@Api(value = "用户控制器", tags = "用户管理")
public class UserController {

    @PostMapping("/add")
    @ApiOperation(value = "添加用户",notes = "")
    public R add(@RequestBody User user) {

        return null;
    }
}