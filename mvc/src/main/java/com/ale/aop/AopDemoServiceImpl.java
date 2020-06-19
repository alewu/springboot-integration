package com.ale.aop;

import org.springframework.stereotype.Service;

/**
 * The type Aop demo service.
 *
 * @author alewu
 * @date 2020 /6/19
 */
@Service
public class AopDemoServiceImpl implements AopDemoService {

    @Override
    public BaseDoor open(BaseDoor door) {
        return door;
    }
}
