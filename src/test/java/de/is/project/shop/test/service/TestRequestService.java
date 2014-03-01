package de.is.project.shop.test.service;
import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;


import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.domain.Request;
import de.is.project.shop.api.domain.ShoppingCart;
import de.is.project.shop.api.services.RequestService;
import de.is.project.shop.impl.domain.ProductImpl;
import de.is.project.shop.impl.domain.RequestImpl;
import de.is.project.shop.impl.domain.ShoppingCartImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class TestRequestService {

	@Inject
	private RequestService rservice;

	@Before
	public void setUp() {
		
		Request request = new RequestImpl();
		rservice.setRequest(request);
	}
	
	@Test
	public void testAddRemoveProduct() {
		
		Product product1 = new ProductImpl();
		product1.setName("Kuehlschrank1");
		product1.setDescription("Niedriger Verbrauch zum spitzen Preis");
		product1.setId(1);
		product1.setPrice(10.0);
		rservice.addProduct(product1);
		rservice.addProduct(product1);
		Assert.isTrue(rservice.getRequest().getItems().size() == 1);
		Assert.isTrue(rservice.getRequest().getTotal() == 20);
		
		Product product3 = new ProductImpl();
		product3.setName("Kuehlschrank2");
		product3.setDescription("Niedriger Verbrauch zum spitzen Preis");
		product3.setId(2);
		product3.setPrice(13.0);
		rservice.addProduct(product3);
		Assert.isTrue(rservice.getRequest().getItems().size() == 2);
		Assert.isTrue(rservice.getRequest().getTotal() == 33);
		
		rservice.removeProduct(product1);
		Assert.isTrue(rservice.getRequest().getItems().size() == 2);
		Assert.isTrue(rservice.getRequest().getTotal() == 23);
		
		rservice.removeProduct(product1);
		Assert.isTrue(rservice.getRequest().getItems().size() == 1);
		Assert.isTrue(rservice.getRequest().getTotal() == 13);
		
		rservice.removeProduct(product3);
		Assert.isTrue(rservice.getRequest().getItems().size() == 0);
		Assert.isTrue(rservice.getRequest().getTotal() == 0);
		
		rservice.addProduct(product1);
		rservice.resetRequest();
		Assert.isTrue(rservice.getRequest().getItems().size() == 0);
		Assert.isTrue(rservice.getRequest().getTotal() == 0);
	}
}
