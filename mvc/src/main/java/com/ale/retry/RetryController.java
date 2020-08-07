package com.ale.retry;

import com.github.rholder.retry.RetryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @author alewu
 * @date 2020/7/31
 */
@RestController
@RequestMapping("/retry")
@Slf4j
public class RetryController {

    /**
     * @author alewu
     * @date 2020/7/31 13:42
     */
    @GetMapping("")
    public ResponseEntity<String> retry() {
        CustomRetryerBuilder customRetryerBuilder = new CustomRetryerBuilder();
        CustomCallable customCallable = new CustomCallable();
        try {
            customRetryerBuilder.<Boolean>build().call(customCallable);
        } catch (
                ExecutionException e) {
            log.error("execute retry failed", e);
        } catch (
                RetryException e) {
            log.error("retry failed", e);
        }
        return ResponseEntity.ok("");
    }
}
