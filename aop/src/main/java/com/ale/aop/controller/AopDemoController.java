package com.ale.aop.controller;

import com.ale.aop.bean.BaseDoor;
import com.ale.aop.bean.IronDoor;
import com.ale.aop.service.AopDemoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alewu
 * @date 2020/6/18
 */
@AllArgsConstructor
@RestController
@Slf4j
public class AopDemoController {
    private final AopDemoService aopDemoService;

    /**
     * @author alewu
     * @date 2020/6/18 19:42
     */
    @GetMapping("/aop")
    public String play() {
        BaseDoor door = new IronDoor();
        door.setColor("blue");
        BaseDoor door1 = aopDemoService.open(door);
        log.info("open {}", door1);
        return door1.toString();
    }
}
