package com.ale.service;

import com.ale.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * The interface User service.
 *
 * @author ale
 */
public interface UserService extends IService<User> {
    /**
     * Update password int.
     *
     * @param name the name
     * @return the int
     */
    User selectUser(String name);
}
