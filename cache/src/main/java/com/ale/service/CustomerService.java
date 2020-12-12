package com.ale.service;

import com.ale.model.Customer;

/**
 * The interface Customer service.
 *
 * @author alewu
 * @date 2020 /7/7
 */
public interface CustomerService {
    /**
     * Gets customer.
     *
     * @param customerId the customer id
     * @return the customer
     */
    Customer getCustomer(final Long customerId);
}
