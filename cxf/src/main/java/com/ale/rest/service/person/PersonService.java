package com.ale.rest.service.person;

import com.ale.domain.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Hello world!
 *
 * @author alewu
 * @date 2019-08-02
 */
@Path("/person")
@Api(value = "/person")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface PersonService {
    /**
     * 测试方法，返回媒体类型 TEXT_PLAIN
     *
     * @return String
     */
    @GET
    @Path("/person")
    Person getPerson();

}
