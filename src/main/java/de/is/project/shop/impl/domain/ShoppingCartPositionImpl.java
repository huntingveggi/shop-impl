package de.is.project.shop.impl.domain;

import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.domain.ShoppingCartPosition;

public class ShoppingCartPositionImpl implements ShoppingCartPosition{

	private Product produkt;
	private int quantity;
	private double total;

	@Override
	public Product getProduct() {
		return this.produkt;
	}

	@Override
	public void setProduct(Product product) {	
		this.produkt = product;
	}

	@Override
	public int getQuantity(){
		return this.quantity;
	}

	@Override
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public double getTotal() {
		return total;
	}

	@Override
	public void setTotal(double total) {
		this.total = total;
	}

}
