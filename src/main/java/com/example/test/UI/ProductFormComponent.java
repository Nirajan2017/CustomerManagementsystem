package com.example.test.UI;

import com.example.test.Entities.Product;
import com.example.test.Service.ProductService;
import com.example.test.UI.design.ProductFormDesign;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Notification;

@UIScope
public class ProductFormComponent extends ProductFormDesign{
	private static final long serialVersionUID = 1L;
	private final ProductService productService;
	
	private final ProductComponent productComponent;
	private BeanFieldGroup<Product> binder;
	private Product product;

	public ProductFormComponent(ProductService productService, ProductComponent productComponent, Product product) {
		this.productService = productService;
		this.productComponent = productComponent;
		buildBeanBinder(product);
		buildControls();
	}
	
	private void buildBeanBinder(Product product) {
		binder = new BeanFieldGroup<Product>(Product.class);	
		this.product = product;
		binder.bindMemberFields(this);
		binder.setItemDataSource(this.product);
	}

	private void buildControls() {
		save.addClickListener(event -> {
			commitBinder();
			System.out.println("----------------------------");
			System.out.println("product name: " + binder.getItemDataSource().getBean().getProduct_name());
			System.out.println("product category: " + binder.getItemDataSource().getBean().getProduct_category());
			System.out.println("product rate: " + binder.getItemDataSource().getBean().getRate());
			
			// validation
			if(binder.getItemDataSource().getBean().getProduct_name().equals("")) {
				Notification.show("Please enter product name !");
			}else if(binder.getItemDataSource().getBean().getProduct_category().equals("")) {
				Notification.show("Please enter product category !");
			}else if(binder.getItemDataSource().getBean().getRate() == 0){
				Notification.show("Please enter valid rate !");
			}else {
				// save PRODUCT entity
				productService.addProduct(binder.getItemDataSource().getBean());
				this.product = new Product();
				binder.setItemDataSource(this.product);
				clearFields();
			}		
			
			// populate the item instance on the Grid
			productComponent.listProducts(null); // not sure about this approach
			
		});
		
		update.addClickListener(event -> {
			commitBinder();			
			productService.updateProduct(binder.getItemDataSource().getBean());
			productComponent.listProducts(null);
			clearFields();
		});
		
		delete.addClickListener(event -> {
			commitBinder();
			productService.removeProduct(binder.getItemDataSource().getBean().getProduct_id());
			productComponent.listProducts(null);
			clearFields();
		});
		
		cancel.addClickListener(event -> {
			setVisible(false);
			productComponent.isFormOpen = false;
		});		
	}
	
	private void commitBinder() {
		try {
			binder.commit(); // important		
			
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void clearFields() {
		product_name.clear();
		product_category.clear();
		rate.setValue("0");
	}
}
