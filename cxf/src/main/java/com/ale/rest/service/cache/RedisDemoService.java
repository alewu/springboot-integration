package com.ale.rest.service.cache;

import com.ale.domain.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
/**
  *  redis 测试缓存
  * @author alewu
  * @date 2019/8/25
  */
@Path("/redis")
@Api(value = "缓存测试")
public interface RedisDemoService {
    /**
     * 测试方法，返回媒体类型 TEXT_PLAIN
     * @param name 参数
     * @return String
     */
    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    @ApiOperation(
            value = "测试缓存",
            notes = "测试缓存-设置缓存"
    )
    Student testCache(@PathParam("name") String name);
}
