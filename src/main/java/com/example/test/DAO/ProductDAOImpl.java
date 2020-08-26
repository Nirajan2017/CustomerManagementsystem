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
import com.example.test.Entities.Product;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	public ProductDAOImpl(final EntityManagerFactory entityManagerFactory) {
		this.entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public void addProduct(Product product) {
		entityManager.persist(product);
	}

	@Override
	public void updateProduct(Product product) {
		entityManager.merge(product);		
	}

	@Override
	public List<Product> listProduct() {
		Query query = this.entityManager.createQuery("FROM Product");
		return query.getResultList();
	}

	@Override
	public List<Product> getProductByName(String text) {
		Query query = this.entityManager.createQuery("FROM Product as p where p.product_name LIKE :startsWith");
		query.setParameter("startsWith", "%"+text+"%");
		return query.getResultList();
	}

	@Override
	public Product getProductById(Long id) {		
		return entityManager.find(Product.class, id);
	}

	@Override
	public void removeProduct(Long id) {
		Product product = entityManager.find(Product.class, id);
		entityManager.remove(product);
	}

	@Override
	public List<Product> listProductCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> listProductNameByCategory(String category) {
		Query query = this.entityManager.createQuery("FROM Product as p where p.product_category = ?");
		query.setParameter(1, category);
//		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return query.getResultList();
	}
}

