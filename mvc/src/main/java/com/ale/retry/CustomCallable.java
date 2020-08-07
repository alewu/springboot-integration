package com.ale.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * @author alewu
 * @date 2020/8/7
 */
@Component
@Slf4j
public class CustomCallable implements Callable<Boolean> {
    private int times;

    @Override
    public Boolean call() throws Exception {
        this.times++;
        if (times == 1) {
            throw new NullPointerException();
        } else if (times == 2) {
            throw new RuntimeException();
        } else if (times == 3) {
            throw new Exception();
        } else if (times == 4) {
            return false;
        } else {
            return true;
        }
    }
}
