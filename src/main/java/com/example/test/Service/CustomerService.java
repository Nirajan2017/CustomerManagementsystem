package com.example.test.Service;

import java.util.List;

import com.example.test.Entities.Customer;

public interface CustomerService {
	public void addCustomer(Customer customer);
	public void updateCustomer(Customer customer);
	public List<Customer> listCustomer();
	public List<Customer> getCustomerByName(String text);
	public Customer getCustomerById(Long id);
	public Customer getCustomer(String name);
	public void removeCustomer(Long id);
}
