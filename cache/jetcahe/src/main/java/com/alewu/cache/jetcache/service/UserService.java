package com.alewu.cache.jetcache.service;

import com.alewu.cache.jetcache.dto.User;
import com.alicp.jetcache.anno.Cached;

public interface UserService {
    @Cached(name = "loadUser", expire = 10)
    User loadUser(long userId);
}
