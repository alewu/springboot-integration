package com.ale.retry;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author alewu
 * @date 2020/7/31
 */
@RestController
@RequestMapping("/retry")
public class RetryController {

    /**
     * @author alewu
     * @date 2020/7/31 13:42
     */
    @GetMapping("")
    public ResponseEntity<String> retry() {
        Callable<Boolean> callable = () -> {
            return false; // do something useful here
        };


        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder().
                //如果异常会重试
                        retryIfException().
                //如果结果为false会重试
                        retryIfResult(Predicates.equalTo(false)).
                //重调策略
                        withWaitStrategy(WaitStrategies.incrementingWait(3, TimeUnit.SECONDS, 3, TimeUnit.SECONDS)).
                //尝试次数
                        withStopStrategy(StopStrategies.stopAfterAttempt(2)).
                //注册监听
                        withRetryListener(new MyRetryListener()).build();
        try {
            retryer.call(callable);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RetryException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("");
    }
}
