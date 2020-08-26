package com.example.test.UI.design;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ComponentDesignTemplate extends VerticalLayout{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected TextField search;
	protected Button addButton, clearFilterTextBtn, deleteButton;
	protected HorizontalLayout toolBarHorizontalLayout;
	protected CssLayout cssLayout;
	public Grid gridComponent;
	protected HorizontalLayout gridAndFormHorizontalLayout;
	
	public ComponentDesignTemplate() {
		// TextField Component
		search = new TextField();
		search.setInputPrompt("Search ...");
		
		// Button Component
		clearFilterTextBtn = new Button(FontAwesome.TIMES);
		addButton = new Button("Add");
		addButton.setIcon(FontAwesome.PLUS);
		deleteButton = new Button("Delete");
		deleteButton.setIcon(FontAwesome.MINUS);
		
    	// Css Layout Component
		cssLayout = new CssLayout();
		cssLayout.addComponents(search, clearFilterTextBtn);
		
		toolBarHorizontalLayout = new HorizontalLayout();
		toolBarHorizontalLayout.addComponents(cssLayout, addButton, deleteButton);
		
		// Grid Layout Component
		gridComponent = new Grid();
		gridComponent.setSizeFull(); 

		gridAndFormHorizontalLayout = new HorizontalLayout(gridComponent);
		gridAndFormHorizontalLayout.setSizeFull();
		gridAndFormHorizontalLayout.setExpandRatio(gridComponent, 1);
		
		
		
		addComponents(toolBarHorizontalLayout, gridAndFormHorizontalLayout);
	}
}
