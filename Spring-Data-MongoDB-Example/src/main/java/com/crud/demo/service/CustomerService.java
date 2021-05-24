package com.crud.demo.service;


import com.crud.demo.Constants;
import com.crud.demo.dal.CustomerRepository;
import com.crud.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    Constants constants;


    public ResponseEntity createCustomer(Customer customer) {

        customerRepository.save(customer);
        return new ResponseEntity(constants.CREATED_SUCCESS, HttpStatus.OK);
    }

    @Cacheable("getAllCustomer")
    public List<Customer> getAllCustomers() {

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return customerRepository.findAll();
    }


    public ResponseEntity getCustomer(String customerId) {

        Customer customer = customerRepository.findOne(customerId);


        if (customer.isStatus() == false)
            return new ResponseEntity(constants.NOT_VALID_CUS, HttpStatus.UNAUTHORIZED);

        else return new ResponseEntity(customer, HttpStatus.OK);
    }


    @CacheEvict("getAllCustomer")
    public ResponseEntity deleteCustomer(String customerId) {

        Customer customer = customerRepository.findOne(customerId);
        customerRepository.delete(customer);
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.OK);
        return responseEntity;
    }


    @CacheEvict("getAllCustomer")
    public ResponseEntity updateCustomer(Customer customer) {

        customerRepository.save(customer);
        return new ResponseEntity(HttpStatus.OK);
    }
}
