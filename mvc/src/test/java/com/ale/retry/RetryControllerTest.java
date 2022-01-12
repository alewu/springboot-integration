package com.ale.retry;

import com.ale.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Testing MVC Web Controllers with Spring Boot and @WebMvcTest
 * https://reflectoring.io/spring-boot-web-controller-test
 * <p>
 * Verifying Controller Responsibilities with @WebMvcTest
 */
// @ExtendWith(SpringExtension.class) included in @WebMvcTest
@WebMvcTest(RetryController.class)
@Import({CustomRetryerBuilder.class, CustomCallable.class})
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

    @Test
    void test() {
        User user = new User();
        user.setAge(1);
        user.setUserName("jack");
        user.setStatus(1);
        User user1 = new User();
        BeanUtils.copyProperties(user, user1);
        System.out.println(user1);
    }
}