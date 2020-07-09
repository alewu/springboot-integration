package com.ale.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author alewu
 */
@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl {
    private final OrderMapper orderMapper;
    private final ApplicationEventPublisher publisher;

    @Transactional(rollbackFor = Exception.class)
    public void placeOrder(Order order) {

        // 保存订单
        orderMapper.insert(order);

        // 发布下单事件
        log.info("publish order event, orderId:{}", order.getId());
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        BeanUtils.copyProperties(order, orderCreatedEvent);
        publisher.publishEvent(orderCreatedEvent);
    }
}
