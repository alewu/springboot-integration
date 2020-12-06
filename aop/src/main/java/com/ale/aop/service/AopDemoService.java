package com.ale.aop.service;

import com.ale.aop.bean.BaseDoor;

/**
 * The interface Aop demo service.
 *
 * @author alewu
 * @date 2020 /6/19
 */
public interface AopDemoService {

    /**
     * Open base door.
     *
     * @param door the door
     * @return the base door
     */
    BaseDoor open(BaseDoor door);
}
