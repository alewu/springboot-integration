package com.ale.config;

import com.ale.rest.service.StudentService;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.common.collect.ImmutableList;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.apache.cxf.jaxrs.validation.ValidationExceptionMapper;
import org.apache.cxf.validation.BeanValidationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * rest service 配置类
 *
 * @author alewu
 * @date 2019-08-02
 */
@Configuration
public class RestServiceConfig {

    @Resource
    private StudentService studentService;

    @Resource
    private Bus bus;

    @Resource
    private JacksonJsonProvider jsonProvider;

    @Resource
    private ValidationExceptionMapper validationExceptionMapper;

//    @Resource
//    private ServiceExceptionMapper serviceExceptionMapper;

    @Resource
    private ApiOriginFilter originFilter;

    @Resource
    private BeanValidationFeature beanValidationFeature;

    @Resource
    private Swagger2Feature swagger2Feature;

    @Resource
    private LoggingFeature loggingFeature;

    @Resource
    private ResponseOutInterceptor globalResponseOutInterceptor;


    @Bean
    public Server server() {
        JAXRSServerFactoryBean jaxrsServerFactoryBean = new JAXRSServerFactoryBean();
        jaxrsServerFactoryBean.setAddress("/testService");
        jaxrsServerFactoryBean.setServiceBeans(ImmutableList.of(studentService));
        jaxrsServerFactoryBean.setBus(bus);

        jaxrsServerFactoryBean.setOutInterceptors(Collections.singletonList(globalResponseOutInterceptor));
        jaxrsServerFactoryBean.setFeatures(ImmutableList.of(beanValidationFeature, swagger2Feature, loggingFeature));
        jaxrsServerFactoryBean.setProviders(ImmutableList.of(jsonProvider, validationExceptionMapper, originFilter));

        return jaxrsServerFactoryBean.create();
    }

}
