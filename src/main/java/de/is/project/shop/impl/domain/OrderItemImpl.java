package de.is.project.shop.impl.domain;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import de.is.project.shop.api.domain.OrderItem;
import de.is.project.shop.api.domain.Product;

public class OrderItemImpl extends AbstractEntity implements OrderItem {

	Product product;
	int quantity;
	double price;
	int reservedQuantity;
	double discount;
	String status;

	@Override
	@ManyToOne(targetEntity = ProductImpl.class)
	public Product getProduct() {
		return this.product;
	}

	@Override
	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	@Column(name = "Quantity", nullable = false)
	public int getQuantity() {
		return this.quantity;
	}

	@Override
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	@Column(name = "Price", nullable = false)
	public double getPrice() {
		return this.price;
	}

	@Override
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	@Column(name = "ReservedQuantity", nullable = false)
	public int getReservedQuantity() {
		return this.reservedQuantity;
	}

	@Override
	public void setReservedQuantity(int reservedQuantity) {
		this.reservedQuantity = reservedQuantity;
	}

	@Override
	@Column(name = "Discount", nullable = true)
	public double getDiscount() {
		return this.discount;
	}

	@Override
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Override
	@Column(name = "Status", nullable = true)
	public String getStatus() {
		return this.status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

}
