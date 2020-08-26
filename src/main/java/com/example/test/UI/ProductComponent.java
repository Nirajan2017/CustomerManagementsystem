package com.example.test.UI;


import org.springframework.beans.factory.annotation.Autowired;

import com.example.test.Entities.Customer;
import com.example.test.Entities.Product;
import com.example.test.Service.ProductService;
import com.example.test.UI.design.ComponentDesignTemplate;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Notification;

import org.springframework.util.StringUtils;

@UIScope
@SpringView(name="ProductComponent")
public class ProductComponent extends ComponentDesignTemplate implements View{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ProductService productService;
	public boolean isFormOpen = false;
	private Long productId;
	
	@Autowired
	public ProductComponent(ProductService productService) {
		this.productService = productService;
		buildToolBar();
		buildDisplay();
	}
	
	private void buildDisplay() {
		//listener for double click on the grid
		gridComponent.setColumns("product_id", "product_name", "product_category");
		gridComponent.addItemClickListener(listener -> {
			 if (listener.isDoubleClick()) { 
				 edit((Product)listener.getItemId());					
			 }else {
				 productId = ((Product)listener.getItemId()).getProduct_id();
			 }
			 
		});
		
		// populate grid with container data
		listProducts(null);		
	}

	private void buildToolBar() {
		// listener for the text field to search
		search.addTextChangeListener(event -> listProducts(event.getText()));
		
		// listener for a Button to add new Item
		addButton.addClickListener(event -> edit(new Product()));		
		
		clearFilterTextBtn.addClickListener(event ->search.setValue(""));
		
		deleteButton.addClickListener(event ->{
			if (productId != null) {
				productService.removeProduct(productId);
				listProducts(null);
			}else {
				Notification.show("Please select a grid row to delete");
			}
		});
	}
	
	public void listProducts(String filterText) {
		BeanItemContainer<Product> container;
		if (StringUtils.isEmpty(filterText)) {
			container = new BeanItemContainer<Product>(Product.class, productService.listProduct());

		} else {
			container = new BeanItemContainer<Product>(Product.class, productService.getProductByName(filterText));
		}
		this.gridComponent.setContainerDataSource(container);
    }
	
	private void edit(Product product) {
		if(!isFormOpen) {
			ProductFormComponent productFormComponent = new ProductFormComponent(productService, this, product); // not sure about this approach, what is the better approach
			gridAndFormHorizontalLayout.addComponent(productFormComponent);
			productFormComponent.setVisible(true);
			isFormOpen = true;
		}
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
		
	}
	
	

}
