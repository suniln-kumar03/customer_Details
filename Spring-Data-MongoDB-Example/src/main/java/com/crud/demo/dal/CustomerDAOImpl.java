package com.crud.demo.dal;

import java.util.List;

import com.crud.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Customer> getAllCustomers() {
		return mongoTemplate.findAll(Customer.class);
	}

	@Override
	public Customer getCustomeById(String userId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(userId));
		return mongoTemplate.findOne(query, Customer.class);
	}

	@Override
	public Customer addNewCustomer(Customer customer) {
		mongoTemplate.save(customer);
		// Now, user object will contain the ID as well
		return customer;
	}


}
