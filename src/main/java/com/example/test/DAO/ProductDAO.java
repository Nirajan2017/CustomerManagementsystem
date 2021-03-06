package com.example.test.DAO;

import java.util.List;

import com.example.test.Entities.Product;


public interface ProductDAO {
	public void addProduct(Product product);
	public void updateProduct(Product product);
	public List<Product> listProduct();
	public List<Product> listProductCategory();
	public List<Product> listProductNameByCategory(String category);
	public List<Product> getProductByName(String text);
	public Product getProductById(Long id);
	public void removeProduct(Long id);
}
