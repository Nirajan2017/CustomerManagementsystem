package com.example.test.DAO;

import java.util.List;

import com.example.test.Entities.Item;


public interface ItemDAO {
	public void addItem(Item item);
	public void updateItem(Item item);
	public List<Item> listItem();
	public List<Item> getItemByName(String text);
	public Item getItemById(Long id);
	public void removeItem(Long id);
}
