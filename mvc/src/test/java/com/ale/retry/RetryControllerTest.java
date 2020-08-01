package com.ale.retry;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RetryControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void retry() throws Exception {
        // given

        // when
        MockHttpServletResponse response =
                mvc.perform(MockMvcRequestBuilders.get("/retry").accept(MediaType.APPLICATION_JSON))
                   .andReturn()
                   .getResponse();

        // then
    }
}