package com.example.test.UI;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.test.Entities.Customer;
import com.example.test.Entities.Item;
import com.example.test.Entities.Order;
import com.example.test.Entities.Product;
import com.example.test.Service.ItemService;
import com.example.test.Service.OrderService;
import com.example.test.Service.ProductService;
import com.example.test.UI.design.OrderFormDesign;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;

public class OrderFormComponent2 extends OrderFormDesign{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ProductService productService;
	private final OrderService orderService;
	private final ItemService itemService;
	private final OrderComponent2 orderComponent2;	
	private Order order;
	private Item item;
	private BeanFieldGroup<Item> itemBinder;
	private ArrayList<Item> itemCollections;	
	private Long countItems = 0L;
	private double final_price = 0;
	
	public OrderFormComponent2(ProductService productService, OrderComponent2 orderComponent2, OrderService orderService, ItemService itemService, Order order) {
		// TODO Auto-generated constructor stub
		this.productService = productService;
		this.orderComponent2 = orderComponent2;
		this.itemService = itemService;		
		this.orderService = orderService;
		this.order = order;
		
		buildAndBind(order);
		buildControls();
		buildDisplay();
	}
	
	public void buildDisplay() {
		grid = new Grid();
		grid.setColumns("SN", "Item Name", "Qty", "Rate", "Amount", "Discount (%)");
		grid.setHeight("200px");
		addComponent(grid);			
	}

	private void buildAndBind(Order order) {
		// populating combobox with item values
		BeanItemContainer<Product> productContainer = new BeanItemContainer<Product>(Product.class, productService.listProduct());
		items.setItemCaptionPropertyId("product_name");
		items.setNullSelectionAllowed(false);
		items.setContainerDataSource(productContainer);		
	
		itemCollections = new ArrayList<Item>();
		
		itemBinder = new BeanFieldGroup<Item>(Item.class);	
		this.item = new Item();
		itemBinder.bindMemberFields(this);	
		itemBinder.setItemDataSource(this.item);		
	}

	private void buildControls() {	
		placeOrderButton.setEnabled(false);
		
		addItemButton.addClickListener(event -> {
			commitItemBinder();		
			
			// a bit confused on this approach (PRODUCT is be NOT SET without this statement)
			itemBinder.getItemDataSource().getBean().setProduct((Product)items.getValue()); 			
			
			// adding each item to an arrayList
			itemCollections.add(itemBinder.getItemDataSource().getBean());					 
			
			System.out.println("----------------------------");
			System.out.println("item name: " + itemBinder.getItemDataSource().getBean().getProduct().getProduct_name());
			System.out.println("item qty: " + itemBinder.getItemDataSource().getBean().getQty());
			System.out.println("item rate: " + itemBinder.getItemDataSource().getBean().getRate());
			
			countItems++; // to display serial number on the grid
			
			// validation
			if(itemBinder.getItemDataSource().getBean().getProduct().getProduct_name().equals("")) {
				Notification.show("Please enter product name !");
			}else if(itemBinder.getItemDataSource().getBean().getQty() == 0) {
				Notification.show("Please enter valid quantity !");
			}else if(itemBinder.getItemDataSource().getBean().getRate() == 0){
				Notification.show("Please enter valid rate !");
			}else {
				grid.addRow(Long.toString(countItems), ((Product)items.getValue()).getProduct_name(), qty.getValue(), rate.getValue(), amount.getValue(), discount.getValue());
				
				// using container to populate grid
//				BeanItemContainer<Item> itemContainer = new BeanItemContainer<Item>(Item.class, Arrays.asList(itemBinder.getItemDataSource().getBean()));
//				grid.setContainerDataSource(itemContainer);
			}
			
			// set and calculate total price, vat amount
			final_price += Double.parseDouble(amount.getValue());
			orderComponent2.gross_price.setValue(Double.toString(final_price));
			orderComponent2.vat_amount.setValue(Double.toString(final_price * 0.13));
			orderComponent2.net_price.setValue(Double.toString(final_price - (final_price * 0.13)));
			
			placeOrderButton.setEnabled(true); // enabled only after an item is added to for sale
			discount.setEnabled(true); // enabled only after an item is added to for sale
			
			// setting itemBinder with new ITEM object
			this.item = new Item();
			itemBinder.setItemDataSource(this.item);
		});
		
		items.addValueChangeListener(event ->{
			Product product = productService.getProductById(((Product)items.getValue()).getProduct_id());
			rate.setValue(Double.toString(product.getRate()));
		});
		
		placeOrderButton.addClickListener(event -> {	
			// bind order to its respective fields
			orderComponent2.commitOrderBinder();
				
			// set order details
			order = orderComponent2.orderBinder.getItemDataSource().getBean();
			order.setCustomer((Customer)orderComponent2.customer.getValue());

			System.out.println("----------------------------");
			System.out.println("Order Gross Price: " + orderComponent2.orderBinder.getItemDataSource().getBean().getGross_price());
			System.out.println("Order Vat Amount: " + orderComponent2.orderBinder.getItemDataSource().getBean().getVat_amount());
			System.out.println("Order Net Amount: " + orderComponent2.orderBinder.getItemDataSource().getBean().getNet_price());
			
			// save order
			orderService.addOrder(order);
			orderComponent2.listOrders(null);
			
			for(Item item: itemCollections) {
				// set order for each item
				item.setOrder(order);
				// save item
				itemService.addItem(item);
			}			
			itemCollections.clear();	
			
			// setting orderBinder with new ORDER object
			this.order = new Order();
			orderComponent2.orderBinder.setItemDataSource(this.order);
//			removeComponent(grid);
//			buildDisplay();
		});
		
		qty.addTextChangeListener(new TextChangeListener() {
		    public void textChange(TextChangeEvent event) {
		        if(event.getText().length() > 0) {
			        if(!rate.getValue().equals(null) && !qty.getValue().equals(null)) {
				        double rateValue = Double.parseDouble(rate.getValue());
				        double price = rateValue * Double.parseDouble(event.getText());
				        amount.setValue(Double.toString(price));	
			        }
		        }
		        else {
		        	amount.setValue("");
		        }		        	        
		    }
		});
		
		discount.addTextChangeListener(new TextChangeListener() {
			@Override
			public void textChange(TextChangeEvent event) {
				if(event.getText().length() > 0) {
					if(!event.getText().equals(null)) {
						double priceAfterDiscount = Double.parseDouble(orderComponent2.gross_price.getValue()) - ((Double.parseDouble(event.getText())/100) * Double.parseDouble(orderComponent2.gross_price.getValue()));
						orderComponent2.vat_amount.setValue(Double.toString(priceAfterDiscount * 0.13));
						orderComponent2.net_price.setValue(Double.toString(priceAfterDiscount - (priceAfterDiscount * 0.13)));
					}
				}else {
					orderComponent2.vat_amount.setValue(Double.toString(Double.parseDouble(orderComponent2.gross_price.getValue()) * 0.13));
					orderComponent2.net_price.setValue(Double.toString(Double.parseDouble(orderComponent2.gross_price.getValue()) - (Double.parseDouble(orderComponent2.gross_price.getValue()) * 0.13)));
				}
			}			
		});
		
		close.addClickListener(event -> {
			setVisible(false);
			orderComponent2.isFormOpen = false;
			orderComponent2.gridComponent.setData(null);			
		});		
	}
	
	private void commitItemBinder() {
		try {
			itemBinder.commit(); // important		
			
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
