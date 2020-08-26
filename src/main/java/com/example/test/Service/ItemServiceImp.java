package com.example.test.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.DAO.ItemDAO;
import com.example.test.Entities.Item;

@Service
public class ItemServiceImp implements ItemService {

	@Autowired
	private ItemDAO itemDAO;
	
	List<Item> items = new ArrayList<Item>();
	
	@Override
	public void addItem(Item item) {
		this.itemDAO.addItem(item);		
	}

	@Override
	public void updateItem(Item item) {
		this.itemDAO.updateItem(item);
		
	}

	@Override
	public List<Item> listItem() {
//		items.clear();		
//		for (Object[] item : itemDAO.listItem()) {		
//			Item itemEntity = new Item();
//			itemEntity.setItem_id(Long.parseLong(item[0].toString()));
//			itemEntity.setItem_name(item[1].toString());
////			System.out.println("Item Name: " + itemEntity.getItem_name());
//			items.add(itemEntity);
//		}
		return itemDAO.listItem();
	}

	@Override
	public List<Item> getItemByName(String text) {
//		items.clear();		
//		for (Object[] item : itemDAO.getItemByName(text)) {		
//			Item itemEntity = new Item();
//			itemEntity.setItem_id(Long.parseLong(item[0].toString()));
//			itemEntity.setItem_name(item[1].toString());
////			System.out.println("Item Name: " + itemEntity.getItem_name());
//			items.add(itemEntity);
//		}
		return itemDAO.getItemByName(text);
	}

	@Override
	public Item getItemById(Long id) {
		return this.itemDAO.getItemById(id);
	}

	@Override
	public void removeItem(Long id) {
		this.itemDAO.removeItem(id);
		
	}

}
