--获取KEY
local key1 = KEYS[1]
-- 获取ARGV[1],这里对应到应用端是一个List<Map>.
--  注意，这里接收到是的字符串，所以需要用csjon库解码成table类型
local receive_arg_json =  cjson.decode(ARGV[1])
local id = receive_arg_json.id
redis.log(redis.LOG_NOTICE,key1)

if redis.call('get', key1) == id then
    return redis.call('del', key1)
else
    return 0
end
