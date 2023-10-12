package com.alewu.cache.jetcache;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 *
 * @author wywuj
 */
@EnableMethodCache(basePackages = "com.alewu.cache")
@EnableConfigurationProperties
@SpringBootApplication
public class JetcacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(JetcacheApplication.class);
    }
}
