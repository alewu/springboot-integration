package com.ale.mybatis.service.impl;

import com.ale.mybatis.entity.Dept;
import com.ale.mybatis.mapper.DeptMapper;
import com.ale.mybatis.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author alewu
 * @date 2020/9/21
 */
@Service
@Slf4j
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
}
