package com.example.test.UI.design;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class OrderFormDesign extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected TextField  qty, rate, amount, discount;
	protected Label priceBeforeTaxLabel;
    protected Button addItemButton;
    protected Button close;
    protected Button placeOrderButton;
    protected ComboBox items;
    public Grid grid;
	
	public OrderFormDesign() {
		setSizeUndefined();	
		
		// TextField Component
		qty = new TextField("QTY");
		rate = new TextField("RATE");
		rate.setEnabled(false);
		rate.setInputPrompt("0.00");	
		amount = new TextField("AMOUNT");
		amount.setEnabled(false);
		amount.setInputPrompt("0.00");	
		discount = new TextField("DISCOUNT (%)");
		discount.setEnabled(false);
		
		// Button Component
		addItemButton = new Button("Add Item");
		placeOrderButton = new Button("Place Order");
		close = new Button("Close");
		
		// ComboBox Component
		items = new ComboBox("ITEMS");
		
		// Grid Component
//		grid = new Grid();
		
		// Horizontal Component
		HorizontalLayout layout1 = new HorizontalLayout(rate, amount);
		HorizontalLayout layout2 = new HorizontalLayout(items, qty);
		HorizontalLayout layout3 = new HorizontalLayout(addItemButton, placeOrderButton, close);
		layout3.setSpacing(true);

		addComponents(layout1, layout2, layout3, discount);
	}
}
