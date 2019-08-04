package com.ale.config;

import com.ale.rest.service.HelloService;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.common.collect.ImmutableList;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.apache.cxf.jaxrs.validation.ValidationExceptionMapper;
import org.apache.cxf.validation.BeanValidationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Collections;

/**
  *  rest service 配置类
  * @author alewu
  * @date 2019-08-02
  */
@Configuration
public class RestServiceConfig {

    @Resource
    private HelloService helloService;

    @Resource
    private Bus bus;

    @Resource
    private JacksonJsonProvider jsonProvider;

    @Resource
    private ValidationExceptionMapper exceptionMapper;

    @Resource
    private ApiOriginFilter originFilter;

    @Resource
    private BeanValidationFeature beanValidationFeature;

    @Resource
    private Swagger2Feature swagger2Feature;

    @Resource


    @Bean
    public Server server() {
        JAXRSServerFactoryBean jaxrsServerFactoryBean = new JAXRSServerFactoryBean();
        jaxrsServerFactoryBean.setAddress("/helloService");
        jaxrsServerFactoryBean.setServiceBeans(Collections.singletonList(helloService));
        jaxrsServerFactoryBean.setBus(bus);

        jaxrsServerFactoryBean.setFeatures(ImmutableList.of(beanValidationFeature, swagger2Feature));
        jaxrsServerFactoryBean.setProviders(ImmutableList.of(jsonProvider, exceptionMapper, originFilter));

        return jaxrsServerFactoryBean.create();
    }


}
