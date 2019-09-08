package com.ale.rest.service.validation;

import com.ale.domain.Student;
import io.swagger.annotations.Api;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Hello world!
 *
 * @author alewu
 * @date 2019-08-02
 */
@Path("/students")
@Api(value = "学生")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface StudentService {
    /**
     * 测试方法
     *
     * @return String
     * @param name 名字
     */
    @GET
    @Path("/{name}")
    Student getStudent(@PathParam("name") String name);

    /**
     * 测试方法:添加student
     *
     * @return String
     * @param student 学生
     */
    @POST
    @Path("/")
    Student addStudent(@Valid Student student);
}
