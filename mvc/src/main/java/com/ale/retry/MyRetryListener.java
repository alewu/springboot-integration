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
        log.info("重试次数：{}, 重试结果：{}", attempt.getAttemptNumber(), attempt.getResult());
    }
}
