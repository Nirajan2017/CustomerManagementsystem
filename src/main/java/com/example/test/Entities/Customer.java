package com.example.test.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vaadin.data.fieldgroup.PropertyId;


@Entity
@Table(name="tbl_customer")
@SuppressWarnings("serial")
public class Customer implements Serializable, Cloneable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	@PropertyId("customer_id")
	private Long customer_id;
	
	@Column(name = "first_name")
	@PropertyId("first_name")
	private String first_name = "";
	
	@Column(name = "last_name")
	@PropertyId("last_name")	
	private String last_name = "";
	
	@Column(name = "email")
	@PropertyId("email")
	private String email = "";

	@Column(name = "status")
	@PropertyId("status")
	private CustomerStatus status;	
	
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
//	private List<Order>orders;
	
//	@Column(name = "customer_identity")
//	@PropertyId("customer_identity")
//	private String customer_identity;

	public Customer() {
    	
    }    

    public Customer(String first_name, String last_name, String email, String customer_identity, CustomerStatus status) {
    	this.first_name = first_name;
    	this.last_name = last_name;
    	this.status = status;
    	this.email = email;
//    	this.customer_identity = customer_identity;
    }

	/**
	 * Get the value of email
	 *
	 * @return the value of email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the value of email
	 *
	 * @param email
	 *            new value of email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the value of status
	 *
	 * @return the value of status
	 */
	public CustomerStatus getStatus() {
		return status;
	}
	
	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * Set the value of status
	 *
	 * @param status
	 *            new value of status
	 */
	public void setStatus(CustomerStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return first_name + " " + last_name;
	}
	
//    public List<Order> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<Order> orders) {
//		this.orders = orders;
//	}

//	public String getCustomer_identity() {
//		return customer_identity;
//	}
//
//	public void setCustomer_identity(String customer_identity) {
//		this.customer_identity = customer_identity;
//	}

}


