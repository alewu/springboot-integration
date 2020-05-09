package com.ale.controller;

import com.ale.common.BaseResponse;
import com.ale.common.PageInfo;
import com.ale.dto.DeptDTO;
import com.ale.entity.Dept;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public Dept get(@PathVariable Integer id) {
        Dept dept = new Dept();
        dept.setDeptId(id);
        dept.setDeptName("it");
        return dept;
    }

    @PostMapping("/page")
    public Dept page(PageInfo pageInfo, @RequestBody DeptDTO deptDTO) {
        Dept dept = new Dept();
        dept.setDeptId(deptDTO.getDeptId());
        dept.setDeptName(deptDTO.getName());
        log.info("pageInfo:{}", pageInfo);
        return dept;
    }
}