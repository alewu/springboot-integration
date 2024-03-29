package com.ale.redis.client.redisson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.time.Instant;

@EnableScheduling
@Slf4j
@Component
public class Scheduler {
    @Autowired
    private MsgProducer msgProducer;

//    @Scheduled(fixedDelay = 300000)
    public void task() {
        long start = Instant.now().toEpochMilli();
        for (int i = 0; i < 1000; i++) {
            log.info("send msg: {}", i);
            DelayedJob delayedJob = new DelayedJob();
            delayedJob.setId(i);
            delayedJob.setName("rose");
            msgProducer.sendAsync(delayedJob);
        }
        log.info("takes {} ms", Instant.now().toEpochMilli() - start);
    }
}
