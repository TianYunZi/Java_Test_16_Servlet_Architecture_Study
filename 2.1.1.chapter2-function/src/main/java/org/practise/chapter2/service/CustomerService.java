package org.practise.chapter2.service;

import org.practise.chapter2.dao.DatabaseHelper;
import org.practise.chapter2.model.Customer;

import java.util.List;
import java.util.Map;

public class CustomerService {

    /**
     * 获取客户列表.
     *
     * @return
     */
    public List<Customer> getCustomerList() {
        String sql = "select * from tb_customer";
        return DatabaseHelper.queryEntityList(Customer.class, sql);
    }

    /**
     * 获取客户.
     *
     * @return
     */
    public Customer getCustomer(long id) {
        return DatabaseHelper.queryEntity(Customer.class, "select * from tb_customer where id = ?", id);
    }

    /**
     * 创建客户
     */
    public boolean createCustomer(Map<String, Object> fieldMap) {
        return DatabaseHelper.insertEntity(Customer.class, fieldMap);
    }

    /**
     * 更新客户
     */
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        return DatabaseHelper.updateEntity(Customer.class, id, fieldMap);
    }

    /**
     * 删除客户
     */
    public boolean deleteCustomer(long id) {
        return DatabaseHelper.deleteEntity(Customer.class, id);
    }
}
