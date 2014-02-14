package de.is.project.shop.test;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.domain.ShoppingCart;
import de.is.project.shop.api.services.*;
import de.is.project.shop.impl.domain.ProductImpl;
import de.is.project.shop.impl.domain.ShoppingCartImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class TestShoppingCartService {
	
	@Inject
	private ShoppingCartService scs;
	
	@Before
	public void setUp() {
		
		ShoppingCart cart = new ShoppingCartImpl();
		scs.setShoppingCart(cart);
	}
	
	@Test
	public void testAddProduct1() {
		
		Product product1 = new ProductImpl();
		product1.setName("Kuehlschrank1");
		product1.setDescription("Niedriger Verbrauch zum spitzen Preis");
		product1.setId(1);
		product1.setPrice(10.0);
		scs.addProduct(product1);
		scs.addProduct(product1);
		Assert.isTrue(scs.getShoppingCart().getShoppingCartPositions().size() == 1);
		Assert.isTrue(scs.getShoppingCart().getTotal() == 20);
		
		Product product3 = new ProductImpl();
		product3.setName("Kuehlschrank2");
		product3.setDescription("Niedriger Verbrauch zum spitzen Preis");
		product3.setId(2);
		product3.setPrice(13.0);
		scs.addProduct(product3);
		Assert.isTrue(scs.getShoppingCart().getShoppingCartPositions().size() == 2);
		Assert.isTrue(scs.getShoppingCart().getTotal() == 33);
		
		scs.removeProduct(product1);
		Assert.isTrue(scs.getShoppingCart().getShoppingCartPositions().size() == 2);
		Assert.isTrue(scs.getShoppingCart().getTotal() == 23);
		
		scs.removeProduct(product1);
		Assert.isTrue(scs.getShoppingCart().getShoppingCartPositions().size() == 1);
		Assert.isTrue(scs.getShoppingCart().getTotal() == 13);
		
		scs.removeProduct(product3);
		Assert.isTrue(scs.getShoppingCart().getShoppingCartPositions().size() == 0);
		Assert.isTrue(scs.getShoppingCart().getTotal() == 0);
		
		scs.addProduct(product1);
		scs.resetShoppingCart();
		Assert.isTrue(scs.getShoppingCart().getShoppingCartPositions().size() == 0);
		Assert.isTrue(scs.getShoppingCart().getTotal() == 0);
	}
	

}
