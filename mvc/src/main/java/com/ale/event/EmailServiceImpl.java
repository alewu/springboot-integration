package com.ale.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * The type Email service.
 *
 * @author alewu
 */
@Service
@Slf4j
public class EmailServiceImpl {

    /**
     * Send email.
     *
     * @param order the order
     */
    public void sendEmail(OrderCreatedEvent order) {
        System.out.println("发送邮件到: " + order.getUserName().toLowerCase());
    }

    /**
     * Place order notice.
     *
     * @param order the order
     */
    @EventListener
    public void placeOrderNotice(OrderCreatedEvent order) {
        log.info("event listener, {}", order);
        sendEmail(order);
    }

}
