package com.ale.service.impl;

import com.ale.entity.Order;
import com.ale.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author alewu
 */
@Service
@AllArgsConstructor
public class OrderServiceImpl {
    private final OrderMapper orderMapper;
    private final ApplicationEventPublisher publisher;

    @Transactional(rollbackFor = Exception.class)
    public void placeOrder(Order order) {

        // 保存订单
        orderMapper.insert(order);

        // 发布下单事件
        publisher.publishEvent(order);
    }
}
