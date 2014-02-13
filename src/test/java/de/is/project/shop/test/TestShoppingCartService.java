package de.is.project.shop.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.domain.ShoppingCart;
import de.is.project.shop.api.services.*;
import de.is.project.shop.impl.domain.ProductImpl;
import de.is.project.shop.impl.domain.ShoppingCartImpl;
import de.is.project.shop.impl.services.ShoppingCartServiceImpl;

public class TestShoppingCartService {
	
	private ShoppingCartService scs;
	
	@Before
	public void setUp() {
		
		scs = new ShoppingCartServiceImpl();
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
		assertEquals(1, scs.getShoppingCart().getShoppingCartPositions().size());
		assertEquals(20, scs.getShoppingCart().getTotal(), 0);
		
		Product product3 = new ProductImpl();
		product3.setName("Kuehlschrank2");
		product3.setDescription("Niedriger Verbrauch zum spitzen Preis");
		product3.setId(2);
		product3.setPrice(13.0);
		scs.addProduct(product3);
		assertEquals(2, scs.getShoppingCart().getShoppingCartPositions().size());
		assertEquals(33, scs.getShoppingCart().getTotal(), 0);
		
		scs.removeProduct(product1);
		assertEquals(2, scs.getShoppingCart().getShoppingCartPositions().size());
		assertEquals(23, scs.getShoppingCart().getTotal(), 0);
		
		scs.removeProduct(product1);
		assertEquals(1, scs.getShoppingCart().getShoppingCartPositions().size());
		assertEquals(13, scs.getShoppingCart().getTotal(), 0);
		
		scs.removeProduct(product3);
		assertEquals(0, scs.getShoppingCart().getShoppingCartPositions().size());
		assertEquals(0, scs.getShoppingCart().getTotal(), 0);
		
		scs.addProduct(product1);
		scs.resetShoppingCart();
		assertEquals(0, scs.getShoppingCart().getShoppingCartPositions().size());
		assertEquals(0, scs.getShoppingCart().getTotal(), 0);
	}
	

}
