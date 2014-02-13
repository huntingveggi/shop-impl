package de.is.project.shop.impl.domain;

import java.util.Collection;
import java.util.Vector;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.domain.ShoppingCart;
import de.is.project.shop.api.domain.ShoppingCartPosition;
import de.is.project.shop.api.domain.Visitable;
import de.is.project.shop.api.domain.Visitor;

@Named
@Scope("prototype")
public class ShoppingCartImpl implements ShoppingCart, Visitable {
	
	private Collection<ShoppingCartPosition> shoppingCartPositions;
	private Customer customer;
	private double total;
	
	@Override
	public Collection<ShoppingCartPosition> getShoppingCartPositions() {
		if(this.shoppingCartPositions == null){
			this.shoppingCartPositions = new Vector<ShoppingCartPosition>();
		}
		return this.shoppingCartPositions;
	}

	@Override
	public Customer getCustomer() {
		return this.customer;
	}

	@Override
	public void setCustomer(Customer customer) {
		this.customer = customer;

	}

	@Override
	public double getTotal() {
		return this.total;
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
