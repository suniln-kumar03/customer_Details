package com.crud.demo.dal;

import java.util.List;

import com.crud.demo.model.Customer;

public interface CustomerDAO {

	List<Customer> getAllCustomers();

	Customer getCustomeById(String userId);

	Customer addNewCustomer(Customer customer);


}