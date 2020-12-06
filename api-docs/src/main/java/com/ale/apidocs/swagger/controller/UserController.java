package com.ale.apidocs.swagger.controller;

import com.ale.apidocs.swagger.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * http://localhost:11111/swagger-ui/index.html
 *
 * @author alewu
 * @date 2020-04-27 14:28
 */
@Slf4j
@RequestMapping("/user")
@RestController
@AllArgsConstructor
@Api(value = "用户控制器", tags = "用户管理")
public class UserController {

    private Map<Long, User> map = new ConcurrentHashMap<>();

    @PostMapping("/add")
    @ApiOperation(value = "添加用户", notes = "")
    @ApiImplicitParam(name = "user", value = "对象参数", required = true, dataType = "User", dataTypeClass = User.class)
    public ResponseEntity<User> add(@RequestBody User user) {
        user.setUserId(100L);
        map.put(user.getUserId(), user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/query")
    @ApiOperation(value = "查询用户", notes = "")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true)
    public ResponseEntity<User> query(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(map.getOrDefault(userId, new User()));
    }
}