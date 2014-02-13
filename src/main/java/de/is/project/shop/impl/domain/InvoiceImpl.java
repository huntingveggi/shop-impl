package de.is.project.shop.impl.domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.OneToMany;

import de.is.project.shop.api.domain.Invoice;
import de.is.project.shop.api.domain.OrderItem;

public class InvoiceImpl extends AbstractEntity implements Invoice {

	Date invoiceDate;
	Collection<OrderItem> orderItems;

	@Override
	@OneToMany(targetEntity = OrderItemImpl.class)
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

}
