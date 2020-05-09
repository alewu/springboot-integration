项目：
- 统一响应结果
- 统一异常处理
- 统一日志记录
- 统一接口文档
- 分页接口统一
- 统一文件上传

---
####spring boot event


---
####cross origin

SpringBoot 2.x主要提供了两种方式来支持Cors，如下：

@CrossOrigin：默认boolean DEFAULT_ALLOW_CREDENTIALS = false;

| 方式 | 作用范围 | 说明 |
| ------ | ------ | ------|
|@CrossOrigin|	一个Controller中全部接口或其中一个特定的接口|	配置、定制特定的请求接口
|实现WebMvcConfigurer接口|	全部接口	|适用于全局配置
