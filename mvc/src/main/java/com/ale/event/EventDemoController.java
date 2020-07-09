package com.ale.event;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alewu
 * @date 2020/7/9
 */
@RestController
@RequestMapping("/event")
@AllArgsConstructor
public class EventDemoController {
    private final OrderServiceImpl orderService;

    @PostMapping()
    public String createOrder(@RequestBody Order order) {
        orderService.placeOrder(order);
        return "ok";
    }
}
