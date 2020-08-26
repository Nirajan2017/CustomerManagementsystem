package com.example.test.UI;

import com.example.test.Entities.Customer;
import com.example.test.Entities.CustomerStatus;
import com.example.test.Service.CustomerService;
import com.example.test.UI.design.CustomerFormDesign;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Notification;

public class CustomerFormComponent extends CustomerFormDesign{
	private static final long serialVersionUID = 1L;
	private CustomerService customerService;
	private CustomerComponent customerComponent;
	private BeanFieldGroup<Customer> binder;
	private Customer customer;
	
	public CustomerFormComponent() {
		
	}

	public CustomerFormComponent(CustomerService customerService, CustomerComponent customerComponent, Customer customer) {
		this.customerService = customerService;
		this.customerComponent = customerComponent;
		buildAndBind(customer);
		buildControls();
	}
	
	private void buildAndBind(Customer customer) {	 	
    	// populating combobox with status values
    	status.addItems(CustomerStatus.values()); // populating combobox with enum values
    	status.setNullSelectionAllowed(false);
    	
		binder = new BeanFieldGroup<Customer>(Customer.class);	
		this.customer = customer;
		binder.bindMemberFields(this);
		binder.setItemDataSource(this.customer);
	}

	private void buildControls() {
		save.addClickListener(event -> {
			commitBinder();
			
			System.out.println("----------------------------");
			System.out.println("Customer FirstName: " + binder.getItemDataSource().getBean().getFirst_name());
			System.out.println("Customer Lastname: " + binder.getItemDataSource().getBean().getLast_name());
			System.out.println("Customer Email: " + binder.getItemDataSource().getBean().getEmail());
			System.out.println("Customer Firstname: " + binder.getItemDataSource().getBean().getStatus());			

			// save customer
			if(binder.getItemDataSource().getBean().getFirst_name().equals("")) {
				Notification.show("Please enter FirstName !");
			}else if(binder.getItemDataSource().getBean().getLast_name().equals("")) {
				Notification.show("Please enter LastName !");
			}else if(binder.getItemDataSource().getBean().getEmail().equals("")){
				Notification.show("Please enter Email !");
			}else {
				customerService.addCustomer(binder.getItemDataSource().getBean());	
				this.customer = new Customer();
				binder.setItemDataSource(this.customer);
				clearFields();
			}			
			
			// populate the item instance on the Grid
			customerComponent.listCustomers(null); // not sure about this approach			 
		});
		
		update.addClickListener(event -> {
			commitBinder();
			customerService.updateCustomer(binder.getItemDataSource().getBean());
			customerComponent.listCustomers(null);
			clearFields();
		});
		
		delete.addClickListener(event -> {
			commitBinder();
			customerService.removeCustomer(binder.getItemDataSource().getBean().getCustomer_id());
			customerComponent.listCustomers(null);
			clearFields();
		});
		
		cancel.addClickListener(event -> {
			setVisible(false);
			customerComponent.isFormOpen= false;
		});
		
		status.addValueChangeListener(event ->{
			save.setEnabled(true);
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
		first_name.clear();
		last_name.clear();
		email.clear();
	}
	
}
