package com.example.test.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vaadin.data.fieldgroup.PropertyId;

@Entity
@Table(name="tbl_product")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "product_id")
	@PropertyId("product_id")
	private Long product_id;  
	
	@Column(name = "product_name")
	@PropertyId("product_name")
	private String product_name = "";
	
	@Column(name = "product_category")
	@PropertyId("product_category")
	private String product_category = "";
	
	@Column(name = "rate")
	@PropertyId("rate")
	private double rate = 0.0;  

//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
//	private List<Stock>stocks;

	public Product() {

	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_category() {
		return product_category;
	}

	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	
//	public List<Stock> getStocks() {
//		return stocks;
//	}
//
//	public void setStocks(List<Stock> stocks) {
//		this.stocks = stocks;
//	}
	
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	
}
