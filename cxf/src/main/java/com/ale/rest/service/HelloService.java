package com.ale.rest.service;

import com.ale.domain.Person;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
  *  Hello world!
  * @author alewu
  * @date 2019-08-02
  */
@Path("/sayHello")
public interface HelloService {
    /**
     * 测试方法，返回媒体类型 TEXT_PLAIN
     * @param a 参数
     * @return String
     */
    @GET
    @Path("/{a}")
    @Produces(MediaType.TEXT_PLAIN)
    String sayHello(@PathParam("a") String a);

    /**
     * 测试方法，返回媒体类型 TEXT_PLAIN
     * @return String
     */
    @GET
    @Path("/person")
    @Produces(MediaType.APPLICATION_JSON)
    Person getPerson();

}
