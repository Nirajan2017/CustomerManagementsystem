package com.example.test.UI;

import com.vaadin.spring.annotation.SpringUI;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.test.Entities.Customer;
import com.example.test.Entities.CustomerStatus;
import com.example.test.Entities.Product;
import com.example.test.Service.CustomerService;
import com.example.test.Service.ProductService;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */

@SpringUI(path = "MainView")
@Theme("valo")
@Widgetset("com.vaadin.DefaultWidgetSet")
public class MainView extends UI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Button customer;
	protected Button product;
	protected Button order;
	protected HorizontalLayout toolbar;
	protected Panel display;
	protected VerticalLayout verticalLayout;
	private ProductComponent productComponent; // created, otherwise "itemService" needs to be instantiated here and passed through constructor
	private CustomerComponent customerComponent;
	private OrderComponent orderComponent;
	
	@Autowired
	CustomerService customerService;
	@Autowired
	ProductService productService;
	
	@Autowired
	public MainView(CustomerComponent customerComponent, ProductComponent productComponent, OrderComponent orderComponent) {
		this.customerComponent = customerComponent;
		this.productComponent = productComponent;
		this.orderComponent = orderComponent;
	}

	@Override
	protected void init(VaadinRequest request) {
		buildLayout();
		buidControls();	
		
		// load some random data during application startup for testing
		// loadData();		
	}
	
	private void loadData() {
		Customer customer = new Customer();
	    customer.setFirst_name("Nirajan");
	    customer.setLast_name("Shrestha");
	    customer.setEmail("nirajan.shtha@gmail.com");
	    customer.setStatus(CustomerStatus.Customer);
	    
	    customerService.addCustomer(customer);
	    
	    Product product = new Product();
	    product.setProduct_name("Bread");
	    product.setProduct_category("Bakery");
	    product.setRate(0.85);
	    
	    productService.addProduct(product);
		
	}

	private void buidControls() {
		Navigator nav = new Navigator(this, display);
		nav.addView("CustomerComponent", customerComponent);
		nav.addView("ProductComponent", productComponent);
		nav.addView("OrderComponent", orderComponent);
		nav.addView("DefaultComponent", ErrorView.class);
		setNavigator(nav);	
		
		customer.addClickListener(e -> {
			nav.navigateTo("CustomerComponent");
		});

		product.addClickListener(e -> {
			nav.navigateTo("ProductComponent");
		});
		
		order.addClickListener(e -> {
			nav.navigateTo("OrderComponent");
		});
		
		setPollInterval(300);
		
		nav.setErrorView(ErrorView.class);		
	}

	private void buildLayout() {
		verticalLayout = new VerticalLayout();
		verticalLayout.setWidth("100%");
		
		customer = new Button("Customer");
		order = new Button("Order");
		product = new Button("Product");
		
		toolbar = new HorizontalLayout();
		toolbar.setWidth("100%");
		toolbar.setHeight(50, Unit.PIXELS);		
		toolbar.addComponents(customer, order, product);
		
		display = new Panel();
		display.setWidth("100%");
		display.setHeight("100%");
		
		verticalLayout.addComponents(toolbar, display);
		setContent(verticalLayout);
	}

}
