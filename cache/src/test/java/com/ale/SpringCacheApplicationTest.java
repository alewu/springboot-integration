package com.ale;

import com.ale.model.Customer;
import com.ale.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class SpringCacheApplicationTest {
    @Autowired
    private CustomerService customerService;

    @Test
    void testCache() {
        Customer customer = customerService.getCustomer(1L);

    }
}