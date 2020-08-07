package com.ale.retry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(RetryController.class)
@ExtendWith(SpringExtension.class)
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