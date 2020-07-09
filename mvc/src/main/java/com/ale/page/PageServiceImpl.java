package com.ale.page;

import com.ale.common.Page;
import com.ale.dto.UserQuery;
import com.ale.entity.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
/**
 * @author alewu
 * @date 2020/7/9
 */
@Service
public class PageServiceImpl extends ServiceImpl<PageUserMapper, User> implements PageService {
    @Override
    public Page<User> pageXXX(Page<User> page, UserQuery query) {
        return null;
    }
}
