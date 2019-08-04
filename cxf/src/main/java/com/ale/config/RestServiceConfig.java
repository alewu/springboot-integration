package com.ale.config;

import com.ale.rest.service.HelloService;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
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
    private BeanValidationFeature beanValidationFeature;

    @Bean
    public Server server() {
        JAXRSServerFactoryBean jaxrsServerFactoryBean = new JAXRSServerFactoryBean();
        jaxrsServerFactoryBean.setAddress("/helloService");
        jaxrsServerFactoryBean.setServiceBeans(Collections.singletonList(helloService));
        jaxrsServerFactoryBean.setBus(bus);

        jaxrsServerFactoryBean.setFeatures(Collections.singletonList(beanValidationFeature));
        jaxrsServerFactoryBean.setProviders(Collections.singletonList(jsonProvider));

        return jaxrsServerFactoryBean.create();
    }


}
