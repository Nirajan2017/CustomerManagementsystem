package com.example.test.UI;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.example.test.Entities.Customer;
import com.example.test.Entities.Item;
import com.example.test.Entities.Order;
import com.example.test.Service.CustomerService;
import com.example.test.Service.ItemService;
import com.example.test.Service.OrderService;
import com.example.test.Service.ProductService;
import com.example.test.UI.design.ComponentDesignTemplate;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

@UIScope
@SpringView(name="OrderComponent")
public class OrderComponent extends ComponentDesignTemplate implements View{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ProductService productService;
	private CustomerService customerService;
	@Autowired 
	private OrderService orderService;
	@Autowired 
	private ItemService itemService;
	public boolean isFormOpen = false;
	private Long itemId;
	public BeanFieldGroup<Order> orderBinder;
	public DateField date;
	public ComboBox customer;
	public TextField gross_price, vat_amount, net_price;
	
	@Autowired
	public OrderComponent(ProductService productService, CustomerService customerService) {
		this.productService = productService;
		this.customerService = customerService;
		
		buildComponents();
		buildDisplay();		
		buildAndBind();
		buildToolBar();
//		buildFooter();
	}

//	private void buildFooter() {
//		
//		discount.addTextChangeListener(new TextChangeListener() {
//			@Override
//			public void textChange(TextChangeEvent event) {
//				if(event.getText().length() > 0) {
//					if(!event.getText().equals(null)) {
//						double priceAfterDiscount = Double.parseDouble(gross_price.getValue()) - ((Double.parseDouble(event.getText())/100) * Double.parseDouble(gross_price.getValue()));
//						vat_amount.setValue(Double.toString(priceAfterDiscount * 0.13));
//						net_price.setValue(Double.toString(priceAfterDiscount - (priceAfterDiscount * 0.13)));
//					}
//				}else {
//					vat_amount.setValue(Double.toString(Double.parseDouble(gross_price.getValue()) * 0.13));
//					net_price.setValue(Double.toString(Double.parseDouble(gross_price.getValue()) - (Double.parseDouble(gross_price.getValue()) * 0.13)));
//				}
//			}			
//		});		
//	}

	private void buildAndBind() {
		BeanItemContainer<Customer> customerContainer = new BeanItemContainer<Customer>(Customer.class, customerService.listCustomer());
//		customerId.setItemCaptionPropertyId("customer_id"); 
		customer.setNullSelectionAllowed(false);
		customer.setContainerDataSource(customerContainer);

		orderBinder = new BeanFieldGroup<Order>(Order.class);
		orderBinder.setItemDataSource(new BeanItem<Order>(new Order()));
		orderBinder.bindMemberFields(this);
	}

	private void buildComponents() {
		customer = new ComboBox();
		customer.setInputPrompt("Customer Id");
		date = new DateField();
		date.setValue(new Date());
		gross_price = new TextField("GROSS PRICE");
		gross_price.setInputPrompt("0.00");
		gross_price.setEnabled(false);
		vat_amount = new TextField("VAT (13%)");
		vat_amount.setInputPrompt("0.00");
		vat_amount.setEnabled(false);
//		discount = new TextField("DISCOUNT (%)");
//		discount.setValue("0.00");
//		discount.setEnabled(false);
		net_price = new TextField("NET PRICE");
		net_price.setInputPrompt("0.00");
		net_price.setEnabled(false);
		
		toolBarHorizontalLayout.addComponents(date, customer);
		HorizontalLayout footerLayout = new HorizontalLayout(gross_price, vat_amount, net_price);
		footerLayout.setSpacing(true);
		
		addComponents(footerLayout);
	}

	private void buildDisplay() {
		gridComponent.setColumns("SN", "ItemName", "Qty", "Rate", "Amount");
		gridComponent.addItemClickListener(listener -> {
			itemId = Long.valueOf((Integer)listener.getItemId());
			 if (listener.isDoubleClick()) { 
				 edit(itemService.getItemById(itemId));					
			 }			 
		});		
	}

	private void buildToolBar() {
		// listener for the text field to search
		search.addTextChangeListener(event -> listItems(event.getText()));
		
		// listener for a Button to add new Item
		addButton.addClickListener(event ->	edit(new Item()));		
		
		clearFilterTextBtn.addClickListener(event -> search.setValue(""));
		
		deleteButton.addClickListener(event ->{
			if (itemId != null) {
//				itemService.removeItem(itemId);
				orderService.removeOrder(itemId);				
				listItems(null);
			}else {
				Notification.show("Please select a grid row to delete");
			}
		});
		
	}

	private void edit(Item item) {
		if(!isFormOpen) {
			OrderFormComponent orderFormComponent = new OrderFormComponent(productService, this, orderService, itemService, item); // not sure about this approach, what is the better approach
			gridAndFormHorizontalLayout.addComponent(orderFormComponent);
			orderFormComponent.setVisible(true);
			isFormOpen = true;
		}
	}
	
	public void listItems(String filterText) {
		BeanItemContainer<Item> itemContainer;
		if (StringUtils.isEmpty(filterText)) {
			itemContainer = new BeanItemContainer<Item>(Item.class, itemService.listItem());
		} else {
			itemContainer = new BeanItemContainer<Item>(Item.class, itemService.getItemByName(filterText));
		}
		gridComponent.removeAllColumns();
		gridComponent.setContainerDataSource(itemContainer);
	}
	
	public void commitOrderBinder() {
		try {	
			orderBinder.commit();
			
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
