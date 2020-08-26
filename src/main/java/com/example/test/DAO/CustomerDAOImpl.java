package com.example.test.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Entities.Customer;



@Repository
@Transactional
public class CustomerDAOImpl implements CustomerDAO{

	private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	public CustomerDAOImpl(final EntityManagerFactory entityManagerFactory) {
		this.entityManager = entityManagerFactory.createEntityManager();
	}
	
	@Override
	public void addCustomer(Customer customer) {
		this.entityManager.persist(customer);
		logger.info("New Customer saved !!");		
	}

	@Override
	public void updateCustomer(Customer customer) {
		this.entityManager.merge(customer);
		logger.info("Customer updated !!");
	}

	@Override
	public List<Customer> listCustomer() {
		Query query = this.entityManager.createQuery("FROM Customer");
		return query.getResultList();
	}

	@Override
	public List<Customer> getCustomerByName(String text) {
		Query query = this.entityManager.createQuery("FROM Customer as c where c.first_name LIKE :startsWith");
		query.setParameter("startsWith", "%"+text+"%");
		return query.getResultList();
	}

	@Override
	public Customer getCustomerById(Long id) {
		return this.entityManager.find(Customer.class, id);
	}

	@Override
	public Customer getCustomer(String name) {
		Query query = this.entityManager.createQuery("FROM Customer as c where c.first_name = ?1");
		query.setParameter(1, name);
		return (Customer) query.getSingleResult();
	}

	@Override
	public void removeCustomer(Long id) {
		Customer customer = this.entityManager.find(Customer.class, id);
		this.entityManager.remove(customer);
		logger.info("Customer "+ customer.getFirst_name() + " deleted !!");
	}

}
