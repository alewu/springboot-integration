package com.ale.event.service.impl;

import com.ale.event.entity.Order;
import com.ale.event.event.OrderCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author alewu
 */
@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl {
    private final ApplicationEventPublisher publisher;

    public void placeOrder(Order order) {
        // 保存订单
        log.info("save order:{}", order);
        // 发布下单事件
        log.info("publish order event, orderId:{}", order.getId());
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        BeanUtils.copyProperties(order, orderCreatedEvent);
        publisher.publishEvent(orderCreatedEvent);
    }
}
