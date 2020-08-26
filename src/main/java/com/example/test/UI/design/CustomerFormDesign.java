package com.example.test.UI.design;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class CustomerFormDesign extends VerticalLayout{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected TextField first_name, last_name, email;
    protected Button save;
    protected Button delete;
    protected Button cancel;
    protected Button update;
    protected ComboBox status;
    
    protected HorizontalLayout input, operations;
    
	public CustomerFormDesign() {
    	setSizeUndefined();
    	// TextField Component
    	first_name = new TextField("First Name");   
    	last_name = new TextField("Last name");
    	email = new TextField("Email");
    	
    	// Button Component
    	save = new Button("Save");
    	save.setEnabled(false);
    	delete = new Button("Delete");
    	update = new Button("update");
    	cancel = new Button("cancel");
    	
    	// ComboBox Component
    	status = new ComboBox("Status");
    	
    	//Horizontal Layout Component
    	operations = new HorizontalLayout(save, delete, update, cancel);
    	
    	// adding all the components to the Vertical Layout
    	addComponents(first_name, last_name, email, status, operations);
	}
}
