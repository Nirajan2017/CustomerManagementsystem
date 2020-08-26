package com.example.test.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.DAO.OrderDAO;
import com.example.test.Entities.Item;
import com.example.test.Entities.Order;


@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDAO orderDAO; 
	
	List<Order> orders = new ArrayList<Order>();

	@Override
	public void addOrder(Order order) {
		orderDAO.addOrder(order);		
	}

	@Override
	public void updateOrder(Order order) {
		orderDAO.updateOrder(order);		
	}

	@Override
	public List<Order> listOrder() {
		// TODO Auto-generated method stub
		return orderDAO.listOrder();
	}

	@Override
	public List<Order> getOrderByName(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getOrderById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeOrder(Long id) {
		orderDAO.removeOrder(id);
		
	}

}
