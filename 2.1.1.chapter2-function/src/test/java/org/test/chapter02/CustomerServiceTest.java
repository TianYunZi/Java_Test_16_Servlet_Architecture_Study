package org.test.chapter02;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.practise.chapter2.model.Customer;
import org.practise.chapter2.service.CustomerService;

import java.util.List;

public class CustomerServiceTest {

    private final CustomerService customerService;

    public CustomerServiceTest(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Before
    public void init() {
        // TODO: 2017/8/27
    }

    @Test
    public void getCustomerListTest() {
        List<Customer> customerList = customerService.getCustomerList();
        Assert.assertEquals(2, customerList.size());
    }
}
