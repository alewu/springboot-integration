package com.ale.retry;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author alewu
 * @date 2020/8/7
 */
@Component
public class CustomRetryerBuilder {

    public <T> Retryer<T> build() {
        return RetryerBuilder.<T>newBuilder().
                //retryIf 重试条件
                        retryIfExceptionOfType(NullPointerException.class).
                        retryIfRuntimeException().
                        retryIfException().
                //                        retryIfResult().
                //重试等待策略
                        withWaitStrategy(WaitStrategies.incrementingWait(3, TimeUnit.SECONDS, 3, TimeUnit.SECONDS)).
                //重试停止策略
                        withStopStrategy(StopStrategies.stopAfterAttempt(5)).
                //注册监听
                        withRetryListener(new MyRetryListener()).build();
    }
}
