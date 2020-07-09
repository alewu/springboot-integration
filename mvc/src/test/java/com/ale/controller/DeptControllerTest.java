package com.ale.controller;

import com.ale.dto.DeptDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DeptControllerTest {
    @Autowired
    private MockMvc mockMvc;
    //ObjectMapper是一个可以重复使用的对象
    @Autowired
    private ObjectMapper mapper;

    @Test
    @SneakyThrows
    void page() {
        // 入参
        String json = "{\n" +
                "  \"deptId\": 1,\n" +
                "  \"name\": \"demoData\"\n" +
                "}";
        DeptDTO deptDTO = mapper.readValue(json, DeptDTO.class);
        // 构造请求
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.post("/dept/page").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(deptDTO));
        // 执行请求
        mockMvc.perform(requestBuilder)
               .andDo(MockMvcResultHandlers.print())
               .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.data.deptId").value(1));
    }
}