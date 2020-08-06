package com.ale.page;

import com.ale.cache.entity.User;
import com.ale.common.Page;
import com.ale.dto.UserQuery;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * The interface Page service.
 *
 * @author alewu
 */
public interface PageService extends IService<User> {
    /**
     * Page xxx page.
     *
     * @param page the page
     * @param query the query
     * @return the page
     */
    Page<User> pageXXX(Page<User> page, UserQuery query);
}
