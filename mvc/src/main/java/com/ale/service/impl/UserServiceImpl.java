package com.ale.service.impl;

import com.ale.entity.User;
import com.ale.mapper.UserMapper;
import com.ale.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author ale
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User selectUser(String name) {
        User user = this.baseMapper.selectById(1);
        user.setName(name);
        return user;
    }
}
