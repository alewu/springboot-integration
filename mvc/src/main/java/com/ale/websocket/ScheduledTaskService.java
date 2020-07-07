package com.ale.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

/**
 * @author alewu
 * @date 2020/7/7
 */
@Component
@EnableScheduling
@Slf4j
public class ScheduledTaskService {


    /**
     * 每分钟执行一次
     */
    @Scheduled(cron = "0/3 * * * * ?")
    public void test() throws IOException {
        log.info("timer task start");
        Set<WsServerEndpoint> webSocketSet = WsServerEndpoint.getWebSocketSet();
        webSocketSet.forEach(wsServerEndpoint -> {
            try {
                wsServerEndpoint.sendMessage("  定时发送  " + new Date());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        log.info("timer task finished");
    }
}
