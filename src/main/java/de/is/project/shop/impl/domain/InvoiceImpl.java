package de.is.project.shop.impl.domain;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import de.is.project.shop.api.domain.Invoice;
import de.is.project.shop.api.domain.OrderItem;

@Entity(name="invoices")
public class InvoiceImpl extends AbstractEntity implements Invoice {

	Date invoiceDate;
	Collection<OrderItem> orderItems = new LinkedList<>();

	@Override
	//@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(targetEntity = OrderItemImpl.class, cascade = { CascadeType.ALL }, orphanRemoval=true, fetch = FetchType.LAZY)
	@JoinColumn(name="bill_of_delivery_id")
	public Collection<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	@Override
	@Column(name = "InvoiceDate", nullable = false)
	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	@Override
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	public void setOrderItems(Collection<OrderItem> orderItems){
		this.orderItems=orderItems;
	}

}
