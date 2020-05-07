package com.ale.service.impl;

import com.ale.entity.Order;
import com.ale.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ApplicationEventPublisher publisher;

    @Transactional(rollbackFor = Exception.class)
    public void placeOrder(Order order) {
        // 保存订单
        orderMapper.insert(order);

        // 发布下单事件
        publisher.publishEvent(order);
    }
}
