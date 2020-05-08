package com.ale;

import com.ale.entity.Order;
import com.ale.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MySpringbootApplicationTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void placeOrder() {
        Order order = new Order();
        order.setUserName("张三");
        order.setGoodsName("iphone X");
        orderService.placeOrder(order);
    }

}