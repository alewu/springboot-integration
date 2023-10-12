package com.ale.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * The type Lua script service.
 */
@Service
public class LuaScriptServiceImpl implements LuaScriptService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private DefaultRedisScript<Boolean> defaultRedisScript;

    /**
     * Init.
     */
    @PostConstruct
    public void init() {
        defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setResultType(Boolean.class);
        defaultRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/DelKey.lua")));
    }

    @Override
    public Boolean redisAddScriptExec(List<String> keyList, Map<String, Object> argvMap) {
        // 调用脚本并执行
        return redisTemplate.execute(defaultRedisScript, keyList, argvMap);
    }


}
