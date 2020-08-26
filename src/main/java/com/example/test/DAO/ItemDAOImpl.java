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

import com.example.test.Entities.Item;


@Repository
@Transactional
public class ItemDAOImpl implements ItemDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(ItemDAOImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	public ItemDAOImpl(final EntityManagerFactory entityManagerFactory) {
		this.entityManager = entityManagerFactory.createEntityManager();
	}
	
	@Override
	public void addItem(Item item) {
		this.entityManager.persist(item);
		logger.info("New item saved !!");
	}

	@Override
	public void updateItem(Item item) {
		this.entityManager.merge(item);
		logger.info("Item updated !!");		
	}

	@Override
	public List<Item> listItem() {
//		Query query = this.entityManager.createNativeQuery("SELECT * FROM tbl_item");
//		return query.getResultList();
		
		Query query = this.entityManager.createQuery("FROM Item");
		return query.getResultList();
	}

	@Override
	public List<Item> getItemByName(String text) {
//		Query query = this.entityManager.createNativeQuery("SELECT * FROM tbl_item where item_name LIKE "+ "'%" + text + "%'");
//		return query.getResultList();
		
		Query query = this.entityManager.createQuery("FROM Item as i where i.item_name LIKE :startsWith");
		query.setParameter("startsWith", "%"+text+"%");
		return query.getResultList();
	}

	@Override
	public Item getItemById(Long id) {
		return this.entityManager.find(Item.class, id);
	}

	@Override
	public void removeItem(Long id) {
		Item item = this.entityManager.find(Item.class, id);
		this.entityManager.remove(item);		
		logger.info("Item "+ item.getProduct().getProduct_name() + " deleted !!");
	}

}
