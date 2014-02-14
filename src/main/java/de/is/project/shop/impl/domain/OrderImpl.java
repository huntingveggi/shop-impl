package de.is.project.shop.impl.domain;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import de.is.project.shop.api.domain.Address;
import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.domain.Order;
import de.is.project.shop.api.domain.OrderItem;
import de.is.project.shop.api.domain.Visitable;
import de.is.project.shop.api.domain.Visitor;

@Entity(name = "orders")
public class OrderImpl extends AbstractEntity implements Order, Visitable {

	Collection<OrderItem> items = new LinkedList<>();;
	Date orderDate;
	Customer customer;
	Address deliveryAddress;
	Address invoiceAddress;
	double discount;
	String status;
	double total;

	@Override
	@ManyToOne(targetEntity = CustomerImpl.class, cascade = { CascadeType.ALL })
	public Customer getCustomer() {
		return this.customer;
	}

	@Override
	@ManyToOne(targetEntity = AddressImpl.class, cascade = { CascadeType.ALL })
	public Address getDeliveryAddress() {
		// TODO Auto-generated method stub
		return this.deliveryAddress;
	}

	@Override
	@Column(name = "discount", nullable = true)
	public double getDiscount() {
		return this.discount;
	}

	@Override
	@ManyToOne(targetEntity = AddressImpl.class, cascade = { CascadeType.ALL })
	public Address getInvoiceAddress() {
		return this.invoiceAddress;
	}

	@Override
	@OneToMany(targetEntity = OrderItemImpl.class, cascade = { CascadeType.ALL })
	public Collection<OrderItem> getItems() {
		return this.items;
	}

	@Override
	@Column(name = "order_date", nullable = false)
	public Date getOrderDate() {
		return this.orderDate;
	}

	@Override
	@Column(name = "status", nullable = false)
	public String getStatus() {
		return this.status;
	}
	
	@Override
	@Transient
	public double getTotal() {
		return this.total;
	}

	@Override
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	@Override
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Override
	public void setInvoiceAddress(Address invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
	}

	public void setItems(Collection<OrderItem> items) {
		this.items = items;
	}

	@Override
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);		
	}

}
