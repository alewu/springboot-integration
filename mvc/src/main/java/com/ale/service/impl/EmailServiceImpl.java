package com.ale.service.impl;

import com.ale.entity.Order;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * The type Email service.
 *
 * @author alewu
 */
@Service
public class EmailServiceImpl {

    /**
     * Send email.
     *
     * @param order the order
     */
    public void sendEmail(Order order) {
        System.out.println("发送邮件到: " + order.getUserName().toLowerCase());
    }

    /**
     * Place order notice.
     *
     * @param order the order
     */
    @EventListener
    public void placeOrderNotice(Order order) {
        sendEmail(order);
    }

}
