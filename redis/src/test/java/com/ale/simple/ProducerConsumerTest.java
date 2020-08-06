package com.ale.simple;

import com.ale.entity.WxMpKefuMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
public class ProducerConsumerTest {
    @Autowired
    private RedisTemplate<String, String> strRedisTemplate;
    public static BoundListOperations<String, String> listOperations = null;
    public static String key = "";
    @Autowired
    @Qualifier("customExecutorPool")
    private ThreadPoolExecutor customExecutorPool;
    long start = 0;
    long end = 0;

    @BeforeEach
    public void init() throws JsonProcessingException {
        key = "test_list";
        listOperations = strRedisTemplate.boundListOps(key);
        String[] messages = new String[1000];
        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 0; i < 1000; i++) {
            WxMpKefuMessage message = new WxMpKefuMessage();
            message.setToUser("a" + i);
            message.setContent("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            message.setMsgType("text");
            messages[i] = objectMapper.writeValueAsString(message);
        }
        listOperations.rightPushAll(messages);
        String index = listOperations.index(1);
        System.out.println("第一个：" + index);
        start = Instant.now().toEpochMilli();
    }

    @AfterEach
    public void after() {
        end = Instant.now().toEpochMilli();
        System.out.println(String.format("end - start = %d ms", end - start));
    }

    @Test
    public void testLeftPop() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        List<CompletableFuture<Boolean>> futures = new ArrayList<>();
        while (true) {
            long start = Instant.now().toEpochMilli();
            Long size = listOperations.size();
            log.info("get size takes {} ms", Instant.now().toEpochMilli() - start);
            if (!(size > 0)) break;
            CompletableFuture<Boolean> completableFuture =
                    CompletableFuture.supplyAsync(() -> listOperations.leftPop()).thenApplyAsync(msg -> {
                int i = atomicInteger.incrementAndGet();
                boolean a = false;
                if (StringUtils.isNotBlank(msg)) {
                    a = sendMsg(msg);
                }
                return a;
            }, customExecutorPool);
            logThreadPool();
            futures.add(completableFuture);
        }

        CompletableFuture<Void> allFutures =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));

        CompletableFuture<List<Boolean>> allPageContentsFuture =
                allFutures.thenApply(v -> futures.stream().map(CompletableFuture::join)
                                                 .collect(Collectors.toList()));

        List<Boolean> join = allPageContentsFuture.join();
        for (Boolean aBoolean : join) {
            System.out.println(aBoolean);
        }


    }

    private void logThreadPool() {
        int activeCount = customExecutorPool.getActiveCount();
        int corePoolSize = customExecutorPool.getCorePoolSize();
        long taskCount = customExecutorPool.getTaskCount();
        long completedTaskCount = customExecutorPool.getCompletedTaskCount();
        BlockingQueue<Runnable> queue = customExecutorPool.getQueue();
        log.info("queue size:{}", queue.size());
        log.info("已完成任务数量：{}", completedTaskCount);
        log.info("任务数量：{}", taskCount);
        log.info("核心线程数量：{}", corePoolSize);
        log.info("线程池活跃数量：{}", activeCount);
    }

    private boolean sendMsg(String msg) {
        int i = ThreadLocalRandom.current().nextInt(1, 3);
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("send msg: {} takes {} s", msg, i);
        return i % 2 == 0;
    }

}
