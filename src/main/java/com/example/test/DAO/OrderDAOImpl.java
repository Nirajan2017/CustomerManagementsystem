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
import com.example.test.Entities.Order;

@Repository
@Transactional
public class OrderDAOImpl implements OrderDAO{

	private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	public OrderDAOImpl(final EntityManagerFactory entityManagerFactory) {
		this.entityManager = entityManagerFactory.createEntityManager();
	}
	
	@Override
	public void addOrder(Order order) {
		this.entityManager.persist(order);
		logger.info("New Order saved !!");	
		
	}

	@Override
	public void updateOrder(Order order) {
		this.entityManager.merge(order);
		logger.info("Order updated !!");
		
	}

	@Override
	public void removeOrder(Long id) {
		Order order = this.entityManager.find(Order.class, id);
		this.entityManager.remove(order);		
		logger.info("Order "+ order.getCustomer() + " deleted !!");
		
	}

	@Override
	public List<Order> listOrder() {
		Query query = this.entityManager.createQuery("FROM Order");
		return query.getResultList();
	}

	@Override
	public List<Order> getOrderByName(String text) {
		Query query = this.entityManager.createQuery("FROM Order as o where o.customer LIKE :startsWith");
		query.setParameter("startsWith", "%"+text+"%");
		return query.getResultList();
	}

	@Override
	public Order getOrderById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
