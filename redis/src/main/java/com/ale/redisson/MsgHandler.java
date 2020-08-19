package com.ale.redisson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author alewu
 * @date 2020/8/19
 */
@Component
@Slf4j
public class MsgHandler {

    //    @Async("msgExecutorPool")
    public void handle(String msg) {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(2));
        } catch (InterruptedException e) {
            log.error("failed", e);
            Thread.currentThread().interrupt();
        }
        log.info("get from delay queue, {}", msg);
    }
}
