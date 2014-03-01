package de.is.project.shop.impl.domain;

import de.is.project.shop.api.domain.Order;
import de.is.project.shop.api.domain.OrderItem;
import de.is.project.shop.api.domain.Request;
import de.is.project.shop.api.domain.RequestItem;
import de.is.project.shop.api.domain.ShoppingCart;
import de.is.project.shop.api.domain.ShoppingCartPosition;
import de.is.project.shop.api.domain.Visitor;

public class VisitorImpl implements Visitor {

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
	
	
	public void visit(Order order) {
		double sum = 0;
		for (OrderItem item : order.getItems()) {
			sum = sum + calculateTotalForOrderItem(item);
		}
		order.setTotal(sum);
	}

	private double calculateTotalForOrderItem(OrderItem item) {
		item.setTotal(item.getQuantity() * item.getProduct().getPrice());
		return item.getTotal();
	}

	@Override
	public void visit(Request request) {
		double sum = 0;
		for (RequestItem item : request.getItems()) {
			sum = sum + calculateTotalForRequestItem(item);
		}
		request.setTotal(sum);
	}
	
	private double calculateTotalForRequestItem(RequestItem item) {
		item.setTotal(item.getQuantity() * item.getProduct().getPrice());
		return item.getTotal();
	}

}
