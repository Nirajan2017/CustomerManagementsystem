package com.example.test.UI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.example.test.Entities.Customer;
import com.example.test.Entities.Product;
import com.example.test.Service.CustomerService;
import com.example.test.UI.design.ComponentDesignTemplate;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Notification;

/**
 * This class contains the logic
 * 
 * @author Nirajan Shrestha
 *
 */
@UIScope
@SpringView(name = "CustomerComponent")
public class CustomerComponent extends ComponentDesignTemplate implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final CustomerService customerService;
	public boolean isFormOpen = false;
	private Long customerId;
	
	@Autowired
	public CustomerComponent(CustomerService customerService) {
		this.customerService = customerService;
		buildToolBar();
		buildDisplay();
	}

	private void buildDisplay() {
		//listener for double click on the grid
		gridComponent.setColumns("customer_id", "first_name", "last_name","email", "status");
		gridComponent.addItemClickListener(listener -> {
			 if (listener.isDoubleClick()) { 
				 edit((Customer)listener.getItemId());					
			 }
			 else {
				 customerId = ((Customer)listener.getItemId()).getCustomer_id();
			 }
		});
		
		// populate grid with container data
		listCustomers(null);
	}

	private void buildToolBar() {
		// textfield listener
		search.addTextChangeListener(listener-> {
			listCustomers(listener.getText());
		});
		
		// button listener
		addButton.addClickListener(event -> edit(new Customer()));
		clearFilterTextBtn.addClickListener(event -> search.setValue(""));
		deleteButton.addClickListener(event ->{
			if (customerId != null) {
				customerService.removeCustomer(customerId);
				listCustomers(null);
			}else {
				Notification.show("Please select a grid row to delete");
			}
		});
	}
	
	public void listCustomers(String filterText) {
		BeanItemContainer<Customer> container;
		if (StringUtils.isEmpty(filterText)) {
			container = new BeanItemContainer<Customer>(Customer.class, customerService.listCustomer());

		} else {
			container = new BeanItemContainer<Customer>(Customer.class, customerService.getCustomerByName(filterText));
		}
		
		// fills the combobox with customer data
		gridComponent.setContainerDataSource(container);		
    }

	private Object edit(Customer customer) {
		if(!isFormOpen) {
			CustomerFormComponent customerFormComponent = new CustomerFormComponent(customerService, this, customer); // not sure about this approach
			gridAndFormHorizontalLayout.addComponent(customerFormComponent);
			customerFormComponent.setVisible(true);
			isFormOpen = true;
		}
		return null;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
