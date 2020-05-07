package com.ale.service.impl;

import com.ale.entity.Order;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {

    public void sendEmail(Order order) {
        System.out.println("发送邮件到: " + order.getUserName().toLowerCase());
    }

    @EventListener
    public void placeOrderNotice(Order order) {
        sendEmail(order);
    }

}
