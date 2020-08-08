package com.ale.service;

import java.util.List;
import java.util.Map;

/**
 * The interface Lua script service.
 */
public interface LuaScriptService {
    /**
     * List设置lua的KEYS,用Map设置Lua的ARGV[1]
     *
     * @param keyList the key list
     * @param argvMap the argv map
     * @return the boolean
     */
    Boolean redisAddScriptExec(List<String> keyList, Map<String, Object> argvMap);
}
