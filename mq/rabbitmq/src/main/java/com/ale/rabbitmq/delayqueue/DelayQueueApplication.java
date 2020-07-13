package com.ale.rabbitmq.delayqueue;

import com.ale.rabbitmq.producer.DirectQueueProducer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author alewu
 * @date 2020/7/13
 */
@SpringBootApplication
public class DelayQueueApplication {
    public static void main(String[] args) {
        SpringApplication.run(DelayQueueApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(XdelaySender xdelaySender) {
        return args -> {
            Booking booking1 = new Booking();
            booking1.setBookingContent("hhaha");
            booking1.setBookingName("第一本书");
            booking1.setBookingTime(LocalDateTime.now());
            booking1.setOperatorName("hellen");
            Booking booking2 = new Booking();
            booking2.setBookingContent("hhaha");
            booking2.setBookingName("第二本书");
            booking2.setBookingTime(LocalDateTime.now());
            booking2.setOperatorName("hellen");
            Booking booking3 = new Booking();
            booking3.setBookingContent("hhehe");
            booking3.setBookingName("第三本书");
            booking3.setBookingTime(LocalDateTime.now());
            booking3.setOperatorName("hellen");
            xdelaySender.send(booking1, 1);
            xdelaySender.send(booking2, 2);
            TimeUnit.MINUTES.sleep(1);
            xdelaySender.send(booking3, 1);
        };
    }

}
