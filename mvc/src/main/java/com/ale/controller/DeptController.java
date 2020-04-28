package com.ale.controller;

import com.ale.common.BaseResponse;
import com.ale.common.PageInfo;
import com.ale.dto.DeptDTO;
import com.ale.entity.Dept;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        dept.setDeptId(id);
        dept.setName("java");
        return dept;
    }

    @PostMapping("/page")
    public Dept page(PageInfo pageInfo, @RequestBody DeptDTO deptDTO) {
        Dept dept = new Dept();
        dept.setDeptId(deptDTO.getDeptId());
        dept.setName(deptDTO.getName());
        log.info("pageInfo:{}", pageInfo);
        return dept;
    }
}