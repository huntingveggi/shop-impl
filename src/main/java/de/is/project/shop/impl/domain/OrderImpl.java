package de.is.project.shop.impl.domain;

import java.util.Collection;
import java.util.Date;

import de.is.project.shop.api.domain.Address;
import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.domain.Order;
import de.is.project.shop.api.domain.OrderItem;

public class OrderImpl extends AbstractEntity implements Order {
	
	Collection<OrderItem> items;
	Date orderDate;
	Customer customer;
	Address deliveryAddress;
	Address invoiceAddress;
	double discount;
	String status;
	
	
	@Override
	public Collection<OrderItem> getItems() {
		return this.items;
	}

	@Override
	public Date getOrderDate() {
		return this.orderDate;
	}

	@Override
	public void setOrderDate(Date orderDate) {
		this.orderDate=orderDate;
	}

	@Override
	public Customer getCustomer() {
		return this.customer;
	}

	@Override
	public void setCustomer(Customer customer) {
		this.customer=customer;
	}

	@Override
	public Address getDeliveryAddres() {
		// TODO Auto-generated method stub
		return this.deliveryAddress;
	}

	@Override
	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress=deliveryAddress;
	}

	@Override
	public Address getInvoiceAddress() {
		return this.invoiceAddress;
	}

	@Override
	public void setInvoiceAddress(Address invoiceAddress) {
		this.invoiceAddress=invoiceAddress;
	}

	@Override
	public double getDiscount() {
		return this.discount;
	}

	@Override
	public void setDiscount(double discount) {
		this.discount=discount;
	}

	@Override
	public String getStatus() {
		return this.status;
	}

	@Override
	public void setStatus(String status) {
		this.status=status;
	}

}
