package com.example.test.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="tbl_order") 
@SuppressWarnings("serial")
public class Order implements Serializable, Cloneable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long order_id;
	
	@Column(name = "date")
	private Date date = new Date();
	
	@Column(name = "gross_price")
	private double gross_price;
	
//	@Column(name = "discount")
//	private double discount;
	
	@Column(name = "vat_amount")
	private double vat_amount;
	
	@Column(name = "net_price")
	private double net_price;
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "order")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<Item> items;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name ="customer_id")
	private Customer customer;
	
	public Order() {

	}

//	public List<Item> getItems() {
//		return items;
//	}
//
//	public void setItems(List<Item> items) {
//		this.items = items;
//	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}	
	public double getGross_price() {
		return gross_price;
	}

	public void setGross_price(double gross_price) {
		this.gross_price = gross_price;
	}

//	public double getDiscount() {
//		return discount;
//	}
//
//	public void setDiscount(double discount) {
//		this.discount = discount;
//	}

	public double getVat_amount() {
		return vat_amount;
	}

	public void setVat_amount(double vat_amount) {
		this.vat_amount = vat_amount;
	}

	public double getNet_price() {
		return net_price;
	}

	public void setNet_price(double net_price) {
		this.net_price = net_price;
	}
	
}
