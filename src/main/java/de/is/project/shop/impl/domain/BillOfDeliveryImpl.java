package de.is.project.shop.impl.domain;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import de.is.project.shop.api.domain.BillOfDelivery;
import de.is.project.shop.api.domain.OrderItem;

@Entity(name="bills_of_delivery")
public class BillOfDeliveryImpl extends AbstractEntity implements
		BillOfDelivery {

	Collection<OrderItem> orderItems = new LinkedList<>();
	Date deliveryDate;

	@Override
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(targetEntity = OrderItemImpl.class)
	public Collection<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	@Override
	@Column(name = "DeliveryDate", nullable = false)
	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	@Override
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	public void setOrderItems(Collection<OrderItem> orderItems){
		this.orderItems=orderItems;
	}

}
