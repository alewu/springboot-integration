package com.ale.service.impl;

import com.ale.common.Page;
import com.ale.dto.UserQuery;
import com.ale.entity.User;
import com.ale.mapper.UserMapper;
import com.ale.service.PageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PageServiceImpl extends ServiceImpl<UserMapper, User> implements PageService {
    @Override
    public Page<User> pageXXX(Page<User> page, UserQuery query) {
        return null;
    }
}
