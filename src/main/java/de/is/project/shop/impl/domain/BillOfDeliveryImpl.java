package de.is.project.shop.impl.domain;

import java.util.Collection;
import java.util.Date;

import de.is.project.shop.api.domain.BillOfDelivery;
import de.is.project.shop.api.domain.OrderItem;

public class BillOfDeliveryImpl extends AbstractEntity implements
		BillOfDelivery {

	Collection<OrderItem> orderItems;
	Date deliveryDate;

	@Override
	public Collection<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	@Override
	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	@Override
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

}
