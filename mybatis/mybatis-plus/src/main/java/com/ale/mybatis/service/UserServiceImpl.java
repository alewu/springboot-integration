package com.ale.mybatis.service;

import com.ale.mybatis.entity.User;
import com.ale.mybatis.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
