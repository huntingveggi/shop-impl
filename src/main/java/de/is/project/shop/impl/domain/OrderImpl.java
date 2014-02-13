package de.is.project.shop.impl.domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

import de.is.project.shop.api.domain.Address;
import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.domain.Order;
import de.is.project.shop.api.domain.OrderItem;

@Entity(name="OrderItem")
public class OrderImpl extends AbstractEntity implements Order {
	
	Collection<OrderItem> items;
	Date orderDate;
	Customer customer;
	Address deliveryAddress;
	Address invoiceAddress;
	double discount;
	String status;
	
	
	@Override
	@OneToMany(targetEntity=OrderItemImpl.class)
	public Collection<OrderItem> getItems() {
		return this.items;
	}
	
	public void setItems(Collection<OrderItem> items) {
		this.items=items;
	}
	
	@Override
	@Column(name = "OrderDate", nullable = false)
	public Date getOrderDate() {
		return this.orderDate;
	}

	@Override
	public void setOrderDate(Date orderDate) {
		this.orderDate=orderDate;
	}

	@Override
	@ManyToOne(targetEntity=CustomerImpl.class)
	public Customer getCustomer() {
		return this.customer;
	}

	@Override
	public void setCustomer(Customer customer) {
		this.customer=customer;
	}

	@Override
	@Column(name = "DeliveryAddress", nullable = false)
	public Address getDeliveryAddres() {
		// TODO Auto-generated method stub
		return this.deliveryAddress;
	}

	@Override
	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress=deliveryAddress;
	}

	@Override
	@Column(name = "InvoiceAddress", nullable = false)
	public Address getInvoiceAddress() {
		return this.invoiceAddress;
	}

	@Override
	public void setInvoiceAddress(Address invoiceAddress) {
		this.invoiceAddress=invoiceAddress;
	}

	@Override
	@Column(name = "OrderDate", nullable = true)
	public double getDiscount() {
		return this.discount;
	}

	@Override
	public void setDiscount(double discount) {
		this.discount=discount;
	}

	@Override
	@Column(name = "Status", nullable = false)
	public String getStatus() {
		return this.status;
	}

	@Override
	public void setStatus(String status) {
		this.status=status;
	}

}
