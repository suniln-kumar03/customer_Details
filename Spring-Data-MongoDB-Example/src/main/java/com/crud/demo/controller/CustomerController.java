package com.crud.demo.controller;

import java.util.List;

import com.crud.demo.model.Customer;
import com.crud.demo.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crud.demo.dal.CustomerDAO;
import com.crud.demo.dal.CustomerRepository;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final CustomerRepository customerRepository;

	private final CustomerDAO customerDAO;

	private final CustomerService customerService;

	public CustomerController(CustomerRepository customerRepository, CustomerDAO customerDAL,CustomerService customerService) {
		this.customerRepository = customerRepository;
		this.customerDAO = customerDAL;
		this.customerService=customerService;
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public ResponseEntity addNewCustomer(@RequestBody Customer customer) {
		LOG.info("Saving Customer.");
		return customerService.createCustomer(customer);
	}

	@RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET,produces = "application/json")
	public List<Customer> getAllCustomers() {
		LOG.info("Getting all Customers.");
		return customerService.getAllCustomers();
	}

	@RequestMapping(value = "/getCustById/{customerId}", method = RequestMethod.GET,produces = "application/json")
	public ResponseEntity getCustomer(@PathVariable String customerId) {
		LOG.info("Getting Customer with ID: {}.", customerId);
		return customerService.getCustomer(customerId);
	}



	@RequestMapping(value = "/updateCust", method = RequestMethod.POST)
	public ResponseEntity updateCustomer(@RequestBody Customer customer) {
		LOG.info("Saving Customer.");
		return customerService.updateCustomer(customer);
	}

	@RequestMapping(value = "/deleteCust/{customerId}", method = RequestMethod.DELETE)
	public ResponseEntity deleteCustomer(@PathVariable String customerId) {
		LOG.info("Getting Customer with ID: {}.", customerId);
		return customerService.deleteCustomer(customerId);
	}

}