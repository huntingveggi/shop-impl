package de.is.project.shop.impl.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.domain.Request;
import de.is.project.shop.api.domain.RequestItem;

@Entity
@Table(name = "request_items")
public class RequestItemImpl extends AbstractEntity implements RequestItem {

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, targetEntity = RequestImpl.class)
	Request request;

	@OneToMany
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
	@ManyToOne(targetEntity = ProductImpl.class, fetch = FetchType.EAGER)
	public Product getProduct() {
		return product;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	@ManyToOne(targetEntity = RequestImpl.class, fetch = FetchType.EAGER)
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
		if (this.getProduct() == null) {
			this.setQuantity(1);
		}
		this.setPrice(product.getPrice());
		this.setTotal(quantity*this.getPrice());
		this.product = product;
	}

	public void setQuantity(int quantity) {
		if (this.getPrice()!=0.0)
			this.setTotal(quantity*this.getPrice());
		this.quantity = quantity;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	@Override
	public void setTotal(double total) {
		this.total = total;
	}

}
