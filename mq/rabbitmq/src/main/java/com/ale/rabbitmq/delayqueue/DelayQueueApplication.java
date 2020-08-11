package com.ale.rabbitmq.delayqueue;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
            booking1.setBookingName("第一本书");
            booking1.setBookingTime(LocalDateTime.now());
            booking1.setAuthor("jack");
            Booking booking2 = new Booking();
            booking2.setBookingName("第二本书");
            booking2.setBookingTime(LocalDateTime.now());
            booking2.setAuthor("rose");
            Booking booking3 = new Booking();
            booking3.setBookingName("第三本书");
            booking3.setBookingTime(LocalDateTime.now());
            booking3.setAuthor("bob");
            xdelaySender.send(booking1, 10);
            TimeUnit.SECONDS.sleep(10);
            xdelaySender.send(booking2, 20);
            TimeUnit.SECONDS.sleep(10);
            xdelaySender.send(booking3, 10);
        };
    }

    @Bean
    public CommandLineRunner commandLineRunner(XdelaySender xdelaySender) {
        return args -> {
            Order order = new Order();
            order.setId(1);
            order.setOrderTime(LocalDateTime.now());
            order.setPayStatus(1);
            xdelaySender.sendOrder(order, 10);
        };
    }
}
