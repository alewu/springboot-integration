项目：
- 统一响应结果
- 统一异常处理
- 统一日志记录
- 统一接口文档
- 分页接口统一
- 统一文件上传


---
####统一异常处理
@RestControllerAdvice 实现统一处理异常

@ExceptionHandler 处理具体异常

常见异常

- ConstraintViolationException
- MethodArgumentNotValidException
- MissingServletRequestParameterException
- HttpRequestMethodNotSupportedException

@ResponseStatus

---

---
####Async
启用 @EnableAsync 寻找标注 @Async 的方法
注意事项：
@Async 失效


---
####spring boot event
- 定义事件
- 发布事件
- 监听事件并处理

---
####cross origin

SpringBoot 2.x主要提供了两种方式来支持Cors，如下：

@CrossOrigin：默认boolean DEFAULT_ALLOW_CREDENTIALS = false;

| 方式 | 作用范围 | 说明 |
| ------ | ------ | ------|
|@CrossOrigin|	一个Controller中全部接口或其中一个特定的接口|	配置、定制特定的请求接口
|实现WebMvcConfigurer接口|	全部接口	|适用于全局配置

---
####common file 

接入各种云存储，阿里云，腾讯云，。。。

云存储属性配置
xxxProperties

云存储自动配置，客户端等
xxxAutoConfiguration

统一的存储服务接口
FileStorageService

配置`file-storage.oss=aliyun,ucloud`启用不同的云服务

后续接入更多


MultipartFile -> 转换

文件上传前预处理

