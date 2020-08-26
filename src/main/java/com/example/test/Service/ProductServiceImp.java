package com.example.test.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.test.DAO.ProductDAO;
import com.example.test.Entities.Product;


@Service
@Component("itemService")
public class ProductServiceImp implements ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	List<Product> products = new ArrayList<Product>();

	@Override
	public void addProduct(Product product) {
		productDAO.addProduct(product);		
	}

	@Override
	public void updateProduct(Product product) {
		productDAO.updateProduct(product);		
	}

	@Override
	public List<Product> listProduct() {		
		return productDAO.listProduct();
	}

	@Override
	public List<Product> getProductByName(String text) {		
		return productDAO.getProductByName(text);
	}

	@Override
	public Product getProductById(Long id) {		
		return productDAO.getProductById(id);
	}

	@Override
	public void removeProduct(Long id) {
		productDAO.removeProduct(id);		
	}

	@Override
	public List<Product> listProductCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> listProductNameByCategory(String category) {
		// TODO Auto-generated method stub
		return productDAO.listProductNameByCategory(category);
	}
}

