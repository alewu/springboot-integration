package com.ale.cahce.redis;

import com.ale.cahce.redis.service.DefaultCustomerServiceImpl;
import com.ale.cahce.redis.service.DefaultProductServiceImpl;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author wywuj
 */
@Component
public class CustomCacheResolver implements CacheResolver {
    private final CacheManager cacheManager;
    private final CacheManager alternateCacheManager;

    public CustomCacheResolver(final CacheManager cacheManager, CacheManager alternateCacheManager) {
        this.cacheManager = cacheManager;
        this.alternateCacheManager = alternateCacheManager;
    }

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        Collection<String> cacheNames = getCacheNames(context);
        if (cacheNames == null) {
            return Collections.emptyList();
        }
        Collection<Cache> result = new ArrayList<>(cacheNames.size());
        if (context.getTarget() instanceof DefaultProductServiceImpl) {
            for (String cacheName : cacheNames) {
                Cache cache = cacheManager.getCache(cacheName);
                if (cache == null) {
                    throw new IllegalArgumentException("Cannot find cache named '" +
                                                               cacheName + "' for " + context.getOperation());
                }
                result.add(cache);
            }
        }
        if (context.getTarget() instanceof DefaultCustomerServiceImpl) {
            for (String cacheName : cacheNames) {
                Cache cache = alternateCacheManager.getCache(cacheName);
                if (cache == null) {
                    throw new IllegalArgumentException("Cannot find cache named '" +
                                                               cacheName + "' for " + context.getOperation());
                }
                result.add(cache);
            }
        }
        return result;
    }

    protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
        return context.getOperation().getCacheNames();
    }
}
