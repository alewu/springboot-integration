package com.ale.controller;

import com.ale.common.BaseResponse;
import com.ale.entity.Dept;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author win10
 * @date 2020-04-27 15:36
 */
@Slf4j
@RequestMapping("/dept")
@RestController
@AllArgsConstructor
@BaseResponse
public class DeptController {

    @PostMapping("/get")
    public Dept get(Integer id) {
        Dept dept = new Dept();
        dept.setId(id);
        dept.setName("java");
        return dept;
    }
}