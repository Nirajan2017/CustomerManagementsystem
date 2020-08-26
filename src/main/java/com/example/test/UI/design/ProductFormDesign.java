package com.example.test.UI.design;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ProductFormDesign extends VerticalLayout{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected TextField product_name, product_category, rate;
    protected Button save;
    protected Button delete;
    protected Button cancel;
    protected Button update;
    
    public ProductFormDesign() {
    	setSizeUndefined();
    	
    	// TextField Component
    	product_name = new TextField("Product Name"); 
    	product_name.focus();
    	product_name.setInputPrompt("enter item name...");
    	
    	product_category = new TextField("Product Category");
    	rate = new TextField("Rate");
    	
    	// Button Component
    	save = new Button("Save");
    	save.setStyleName(ValoTheme.BUTTON_PRIMARY);
    	save.setClickShortcut(KeyCode.ENTER);

    	delete = new Button("Delete");
    	update = new Button("update");
    	cancel = new Button("cancel");
    	
    	//Horizontal Layout Component
    	HorizontalLayout operations = new HorizontalLayout(save, delete, update, cancel);
    	addComponents(product_name, product_category, rate, operations);    	
    }
}
