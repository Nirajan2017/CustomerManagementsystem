package com.example.test.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.test.DAO.CustomerDAO;
import com.example.test.Entities.Customer;


@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDAO customerDAO;
	
	List<Customer> customers = new ArrayList<Customer>();
	
	@Override
	public void addCustomer(Customer customer) {
		customerDAO.addCustomer(customer);		
	}

	@Override
	public void updateCustomer(Customer customer) {
		customerDAO.updateCustomer(customer);		
	}

	@Override
	public List<Customer> listCustomer() {
		return customerDAO.listCustomer();
	}

	@Override
	public List<Customer> getCustomerByName(String text) {
		return customerDAO.getCustomerByName(text);
	}

	@Override
	public Customer getCustomerById(Long id) {
		return customerDAO.getCustomerById(id);
	}

	@Override
	public Customer getCustomer(String name) {
		return customerDAO.getCustomer(name);
	}

	@Override
	public void removeCustomer(Long id) {
		customerDAO.removeCustomer(id);
		
	}

}
