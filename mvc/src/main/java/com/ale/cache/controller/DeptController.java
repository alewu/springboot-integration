package com.ale.cache.controller;

import com.ale.cache.entity.Dept;
import com.ale.common.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alewu
 * @date 2020-04-27 15:36
 */
@Slf4j
@RequestMapping("/dept")
@RestController
@AllArgsConstructor
@BaseResponse
public class DeptController {

    @GetMapping("/{id:\\d+}")
    public Dept get(@PathVariable Integer id) {
        Dept dept = new Dept();
        dept.setDeptId(id);
        dept.setDeptName("it");
        return dept;
    }
}