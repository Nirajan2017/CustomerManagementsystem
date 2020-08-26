package com.example.test.DAO;

import java.util.List;

import com.example.test.Entities.Item;
import com.example.test.Entities.Order;

public interface OrderDAO {
	public void addOrder(Order order);
	public void updateOrder(Order order);
	public List<Order> listOrder();
	public List<Order> getOrderByName(String text);
	public Order getOrderById(Long id);
	public void removeOrder(Long id);
}
