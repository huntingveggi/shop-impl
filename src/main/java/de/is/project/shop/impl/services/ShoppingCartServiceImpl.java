package de.is.project.shop.impl.services;

import javax.inject.Named;
import org.springframework.context.annotation.Scope;

import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.domain.ShoppingCart;
import de.is.project.shop.api.domain.ShoppingCartPosition;
import de.is.project.shop.api.domain.Visitor;
import de.is.project.shop.api.services.ShoppingCartService;
import de.is.project.shop.impl.domain.ShoppingCartPositionImpl;
import de.is.project.shop.impl.domain.ShoppingCartVisitor;

@Named
@Scope("prototype")
public class ShoppingCartServiceImpl implements ShoppingCartService {

	private ShoppingCart shoppingCart;
	
	@Override
	public void addProduct(Product product) {
		boolean found = false;
		
		for (ShoppingCartPosition pos : this.shoppingCart
				.getShoppingCartPositions()) {
			if (pos.getProduct().getId() == product.getId()) {
				pos.setQuantity(pos.getQuantity() + 1);
				found = true;
				break;
			}
		}
		if (!found) {
			ShoppingCartPosition pos= new ShoppingCartPositionImpl();
			pos.setProduct(product);
			pos.setQuantity(1);
			pos.setTotal(product.getPrice());
			this.shoppingCart.getShoppingCartPositions().add(pos);
		}
		this.refreshShoppingCart();
	}

	@Override
	public void removeProduct(Product product) {
		for (ShoppingCartPosition pos : this.shoppingCart
				.getShoppingCartPositions()) {
			if (pos.getProduct().getId() == product.getId()) {
				if (pos.getQuantity() < 2) {
					if(this.shoppingCart.getShoppingCartPositions().size() == 1){
						this.shoppingCart.getShoppingCartPositions().clear();
					}else{
						this.shoppingCart.getShoppingCartPositions().remove(pos);
					}
				}else{
					pos.setQuantity(pos.getQuantity()-1);
				}
				break;
			}
		}
		this.refreshShoppingCart();
	}

	@Override
	public void resetShoppingCart() {
		this.shoppingCart.getShoppingCartPositions().clear();
		refreshShoppingCart();
	}
	
	@Override
	public void refreshShoppingCart() {
		Visitor visitor = new ShoppingCartVisitor();
		this.shoppingCart.accept(visitor);
	}

	@Override
	public void setShoppingCart(ShoppingCart cart) {
		if (this.shoppingCart == null) {
			this.shoppingCart = cart;
		}
	}

	@Override
	public ShoppingCart getShoppingCart() {
		return this.shoppingCart;
	}

}
