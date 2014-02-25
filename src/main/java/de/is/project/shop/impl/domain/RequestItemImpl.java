package de.is.project.shop.impl.domain;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.domain.Request;
import de.is.project.shop.api.domain.RequestItem;

public class RequestItemImpl extends AbstractEntity implements RequestItem {

	Request request;
	Product product;
	int quantity;
	double price;
	double discount;
	double total;

	@Override
	public double getDiscount() {
		return discount;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	@ManyToOne(targetEntity=ProductImpl.class, fetch = FetchType.EAGER)
	public Product getProduct() {
		return product;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	@ManyToOne(targetEntity=RequestImpl.class, fetch = FetchType.EAGER)
	public Request getRequest() {
		return request;
	}

	@Override
	@Transient
	public double getTotal() {
		return total;
	}

	@Override
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Override
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public void setQuantity() {
		// TODO Auto-generated method stub

	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public void setRequest() {
		// TODO Auto-generated method stub

	}

	public void setRequest(Request request) {
		this.request = request;
	}

	@Override
	public void setTotal(double total) {
		this.total = total;
	}

}
