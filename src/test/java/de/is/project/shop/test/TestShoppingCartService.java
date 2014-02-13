package de.is.project.shop.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.domain.ShoppingCart;
import de.is.project.shop.api.services.*;

public class TestShoppingCartService {

	private ApplicationContext context;

	@Test
	public void test() {
		context = new ClassPathXmlApplicationContext(
				"spring.xml");
		
		ShoppingCartService scs = context.getBean(ShoppingCartService.class);
		ShoppingCart cart = context.getBean(ShoppingCart.class);
		scs.setShoppingCart(cart);
		Product product1 = context.getBean(Product.class);
		product1.setName("Kuehlschrank1");
		product1.setDescription("Niedriger Verbrauch zum spitzen Preis");
		product1.setId(1);
		product1.setPrice(10.0);
		scs.addProduct(product1);
		scs.addProduct(product1);
		Product product3 = context.getBean(Product.class);
		product3.setName("Kuehlschrank2");
		product3.setDescription("Niedriger Verbrauch zum spitzen Preis");
		product3.setId(2);
		product3.setPrice(13.0);
		
		scs.addProduct(product3);
		assertEquals(33, scs.getShoppingCart().getTotal(), 0);
		
		scs.removeProduct(product1);
		assertEquals(23, scs.getShoppingCart().getTotal(), 0);
		
		scs.resetShoppingCart();
		assertEquals(0, scs.getShoppingCart().getTotal(), 0);
	}

}
