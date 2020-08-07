package com.ale.retry;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @author alewu
 * @date 2020/7/31
 */
@Slf4j
public class MyRetryListener implements RetryListener {
    @Override
    public <V> void onRetry(Attempt<V> attempt) {
        // 距离第一次重试的延迟
        log.info("retry times: {}, Delay Since First Attempt {} ms", attempt.getAttemptNumber(),
                 attempt.getDelaySinceFirstAttempt());

        // 是什么原因导致异常
        if (attempt.hasException()) {
            log.warn("Exception Cause: {}", attempt.getExceptionCause().toString());
        } else {// 正常返回时的结果
            log.info("result {}", attempt.getResult());
        }

    }
}
