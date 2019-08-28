package com.ale.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.apache.cxf.jaxrs.validation.ValidationExceptionMapper;
import org.apache.cxf.validation.BeanValidationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CXF公共配置类
 *
 * @author alewu
 * @date 2019-08-02
 */
@Configuration
public class CxfConfig {

    @Bean
    public BeanValidationFeature beanValidationFeature() {
        return new BeanValidationFeature();
    }

    @Bean("swagger2Feature")
    public Swagger2Feature swagger2Feature() {
        Swagger2Feature swagger2Feature = new Swagger2Feature();
        swagger2Feature.setBasePath("/api");
        swagger2Feature.setScan(true);
        return swagger2Feature;
    }

    @Bean
    public LoggingFeature loggingFeature() {
        return new LoggingFeature();
    }


    @Bean("validationExceptionMapper")
    public ValidationExceptionMapper validationExceptionMapper() {
        return new CustomBeanValidationExceptionMapper();
    }

    @Bean("serviceExceptionMapper")
    public ServiceExceptionMapper serviceExceptionMapper() {
        return new ServiceExceptionMapper();
    }

    @Bean("jsonProvider")
    public JacksonJsonProvider jacksonJsonProvider() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return new JacksonJsonProvider(objectMapper);
    }

    @Bean("responseOutInterceptor")
    public ResponseOutInterceptor responseOutInterceptor() {
        return new ResponseOutInterceptor();
    }

}
