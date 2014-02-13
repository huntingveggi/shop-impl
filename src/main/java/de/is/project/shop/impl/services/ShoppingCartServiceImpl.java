package de.is.project.shop.impl.services;

import javax.inject.Named;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;

import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.domain.ShoppingCart;
import de.is.project.shop.api.domain.ShoppingCartPosition;
import de.is.project.shop.api.domain.Visitor;
import de.is.project.shop.api.services.ShoppingCartService;

@Named
@Scope("prototype")
public class ShoppingCartServiceImpl implements ShoppingCartService, ApplicationContextAware {

	private ShoppingCart shoppingCart;
	
	ApplicationContext applicationContext = null;
	
	
	
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
			ShoppingCartPosition pos= applicationContext.getBean(ShoppingCartPosition.class);
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
		Visitor visitor = applicationContext.getBean(Visitor.class);
		this.shoppingCart.accept(visitor);
	}

	@Override
	public void setShoppingCart(ShoppingCart cart) {
		if (this.shoppingCart == null) {
			this.shoppingCart = cart;
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public ShoppingCart getShoppingCart() {
		return this.shoppingCart;
	}

}
