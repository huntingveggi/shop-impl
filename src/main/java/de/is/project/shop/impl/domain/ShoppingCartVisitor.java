package de.is.project.shop.impl.domain;

import de.is.project.shop.api.domain.ShoppingCart;
import de.is.project.shop.api.domain.ShoppingCartPosition;
import de.is.project.shop.api.domain.Visitor;

public class ShoppingCartVisitor implements Visitor {

	@Override
	public void visit(ShoppingCart shoppingCart) {
		double sum = 0;
		for (ShoppingCartPosition pos : shoppingCart.getShoppingCartPositions()) {
			sum = sum + calculateTotalForPosition(pos);
		}
		shoppingCart.setTotal(sum);
	}

	private double calculateTotalForPosition(ShoppingCartPosition pos) {
		pos.setTotal(pos.getQuantity() * pos.getProduct().getPrice());
		return pos.getTotal();
	}

}
