package com.example.test.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vaadin.data.fieldgroup.PropertyId;

@Entity
@Table(name="tbl_item")
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "item_id")
	@PropertyId("item_id")
	private Long item_id;  
	
	@Column(name = "qty")
	@PropertyId("qty")
	private int qty;
	
	@Column(name = "rate")
	@PropertyId("rate")
	private double rate;
	
	@Column(name = "discount")
	@PropertyId("discount")
	private double discount;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name ="order_id")
	private Order order;	
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name ="product_id")
	private Product items;
	
	public Item() {
		
	}		

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Long getItem_id() {
		return item_id;
	}
	
	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}

	public Product getProduct() {
		return items;
	}

	public void setProduct(Product product) {
		this.items = product;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
}


