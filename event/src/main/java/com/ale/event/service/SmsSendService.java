package com.ale.event.service;

import com.ale.event.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * The type Email service.
 *
 * @author alewu
 */
@Service
@Slf4j
public class SmsSendService {
    /**
     * Place order notice.
     *
     * @param order the order
     */
    @Async
    @EventListener
    public void placeOrderNoticeAsync(OrderCreatedEvent order) {
        log.info("event listener async, {}", order);
        sendSms(order);
    }


    /**
     * Send email.
     *
     * @param order the order
     */
    public void sendSms(OrderCreatedEvent order) {
        log.info("发送短信到: {}", order.getUserName().toLowerCase());
    }


}
