package de.is.project.shop.test;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import de.is.project.shop.api.domain.Address;
import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.domain.Order;
import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.persistence.ProductDAO;
import de.is.project.shop.api.services.OrderService;
import de.is.project.shop.impl.domain.AddressImpl;
import de.is.project.shop.impl.domain.CustomerImpl;
import de.is.project.shop.impl.domain.OrderImpl;
import de.is.project.shop.impl.domain.ProductImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class TestOrderService {
	
	@Inject
	private OrderService service;
	
	@Inject
	private ProductDAO productDAO;
	
	@Before
	public void setUp() {
		
		Order order = new OrderImpl();
		Customer customer = createCustomer();
		Address address = createAddress();
		customer.setAddress(address);
		order.setCustomer(customer);
		
		service.setOrder(order);
	}
	
	private Customer createCustomer(){
		Customer customer = new CustomerImpl();
		customer.setFirstName("Max");
		customer.setLastName("Mustermann");
		customer.setSex("male");
		customer.setTitle("Dr.");
		customer.setBillingCustomer(false);
		
		return customer;
	}
	
	private Address createAddress(){
		Address address = new AddressImpl();
		address.setStreet("Street");
		address.setStreetNumber("1b");
		address.setZipCode("12345");
		address.setCountry("Germany");
		address.setCity("City");
		return address;
	}
	
	@Test
	public void testAddProduct1() {
		
		Product product1 = createProdukt1();
		service.addProduct(product1);
		service.addProduct(product1);
		Assert.isTrue(service.getOrder().getItems().size() == 1);
		Assert.isTrue(service.getOrder().getTotal() == 20);
		
		Product product2 = createProdukt2();
		service.addProduct(product2);
		Assert.isTrue(service.getOrder().getItems().size() == 2);
		Assert.isTrue(service.getOrder().getTotal() == 33);
		
		service.removeProduct(product1);
		Assert.isTrue(service.getOrder().getItems().size() == 2);
		Assert.isTrue(service.getOrder().getTotal() == 23);
		
		service.removeProduct(product1);
		Assert.isTrue(service.getOrder().getItems().size() == 1);
		Assert.isTrue(service.getOrder().getTotal() == 13);
		
		service.removeProduct(product2);
		Assert.isTrue(service.getOrder().getItems().size() == 0);
		Assert.isTrue(service.getOrder().getTotal() == 0);
		
		service.addProduct(product1);
		Assert.isTrue(service.getOrder().getItems().size() == 1);
		Assert.isTrue(service.getOrder().getTotal() == 10);
	}
	
	@Test
	public void testPlaceOrder(){
		Product product1 = createProdukt1();
		Product product2 = createProdukt2();
		
		productDAO.persist(product1);
		productDAO.persist(product2);
		
		service.addProduct(product1);
		service.addProduct(product1);
		service.addProduct(product2);
		
		Order placedOrder= service.placeOrder();
		Assert.isTrue(placedOrder.getId() > - 1);
		
	}
	
	public Product createProdukt1(){
		Product product1 = new ProductImpl();
		product1.setName("Kuehlschrank1");
		product1.setDescription("Niedriger Verbrauch zum spitzen Preis");
		product1.setId(1);
		product1.setPrice(10.0);
		return product1;
	}
	
	public Product createProdukt2(){
		Product product2 = new ProductImpl();
		product2.setName("Kuehlschrank2");
		product2.setDescription("Niedriger Verbrauch zum spitzen Preis");
		product2.setId(2);
		product2.setPrice(13.0);
		return product2;
	}

}