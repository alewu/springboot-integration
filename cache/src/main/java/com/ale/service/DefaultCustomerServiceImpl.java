package com.ale.service;

import com.ale.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author alewu
 * @date 2020/7/7
 */
@Service
public class DefaultCustomerServiceImpl implements CustomerService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultCustomerServiceImpl.class);

    @Cacheable(cacheNames = {"customer"})
    @Override
    public Customer getCustomer(Long customerId) {
        LOG.info("Trying to get customer information for id {} ", customerId);
        return getCustomerData(customerId);
    }

    private Customer getCustomerData(final Long id) {
        return new Customer(id, "testemail@test.com", "Test Customer");
    }
}