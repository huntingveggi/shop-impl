package de.is.project.shop.test;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import de.is.project.shop.api.domain.Address;
import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.domain.Order;
import de.is.project.shop.api.domain.OrderItem;
import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.persistence.OrderDAO;
import de.is.project.shop.impl.domain.AddressImpl;
import de.is.project.shop.impl.domain.CustomerImpl;
import de.is.project.shop.impl.domain.OrderImpl;
import de.is.project.shop.impl.domain.OrderItemImpl;
import de.is.project.shop.impl.domain.OrderStatus;
import de.is.project.shop.impl.domain.PaymentTerm;
import de.is.project.shop.impl.domain.ProductImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
@TransactionConfiguration(defaultRollback = false)
public class TestOrderDAO {

	@Inject
	OrderDAO testDao;
	Collection<Order> createdOrders = new LinkedList<Order>();
	
	
	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
		for (Order o : createdOrders) {
			Order oTest = testDao.findById(o.getId());
			if (oTest != null) {
				testDao.delete(o);
			}
		}
	}
	
	@Test
	public void testPersist() {

		Order testOrder = createOrder();
		double testDiscount = testOrder.getDiscount();
		testDao.persist(testOrder);

		createdOrders.add(testOrder);

		Order persistedOrder = testDao.findById(testOrder.getId());

		Assert.isTrue(testOrder.getId() > -1);
		Assert.notNull(persistedOrder);
		Assert.isTrue(persistedOrder.getId() == testOrder.getId());
		Assert.isTrue(persistedOrder.getDiscount() == testDiscount);

	}

	@Test
	public void testFindById() {
		Order testOrder = createOrder();

		testDao.persist(testOrder);

		createdOrders.add(testOrder);

		Order o = testDao.findById(testOrder.getId());
		Assert.notNull(o);
		Assert.isTrue(o.getId() == testOrder.getId());
	}
	
	@Test
	public void testDelete() {

		Order testOrder = createOrder();

		testDao.persist(testOrder);
		createdOrders.add(testOrder);

		int id = testOrder.getId();
		Assert.isTrue(testOrder.getId() > 0);

		testDao.delete(testOrder);
		Order o = testDao.findById(id);
		Assert.isNull(o);

		createdOrders.remove(testOrder);

	}
	
	@Test
	public void testPersistWithAddingCustomerAndAddresses() {
		
		Customer customer = createCustomer();
		
		Address deliveryAddress = createAddress();
		deliveryAddress.setCustomer(customer);
		
		Address invoiceAddress = createAddress();
		invoiceAddress.setCustomer(customer);

		customer.setAddress(deliveryAddress);
		
		Order testOrder = createOrder();
		testOrder.setDeliveryAddress(deliveryAddress);
		testOrder.setInvoiceAddress(invoiceAddress);
		testOrder.setCustomer(customer);

		testDao.persist(testOrder);
		
		createdOrders.add(testOrder);

		Order o = testDao.findById(testOrder.getId());
		Assert.notNull(o);
		Assert.isTrue(o.getId() == testOrder.getId());
		Assert.isTrue(o.getCustomer().getFirstName().equals(customer.getFirstName()));
		Assert.isTrue(o.getDeliveryAddress().getStreet().equals(deliveryAddress.getStreet()));
		Assert.isTrue(o.getDeliveryAddress().getStreet().equals(deliveryAddress.getStreet()));

	}
	
	@Test
	public void testPersistWithAddingOrderItems() {
		
		Product product1 = new ProductImpl();
		product1.setName("Product1");
		Product product2 = new ProductImpl();
		product1.setName("Product2");
		
		OrderItem item1 = new OrderItemImpl();
		item1.setProduct(product1);
		item1.setDiscount(0.05);
		item1.setQuantity(1);
		item1.setReservedQuantity(1);
		item1.setStatus("Ordered");
		item1.setPrice(20.0);
		
		OrderItem item2 = new OrderItemImpl();
		item2.setProduct(product2);
		item2.setDiscount(0.1);
		item2.setQuantity(1);
		item2.setReservedQuantity(1);
		item2.setStatus("Packaged");
		item2.setPrice(30.0);
		
		Order testOrder = createOrder();
		testOrder.getItems().add(item1);
		testOrder.getItems().add(item2);

		testDao.persist(testOrder);

		createdOrders.add(testOrder);

		Order persistedOrder = testDao.findById(testOrder.getId());

		Assert.isTrue(persistedOrder.getItems().size() == 2);

		OrderItem item3 = new OrderItemImpl();
		item3.setProduct(product2);
		item3.setDiscount(0.1);
		item3.setQuantity(1);
		item3.setReservedQuantity(1);
		item3.setStatus("Delivered");
		item3.setPrice(30.0);
		testOrder.getItems().add(item3);
		testDao.update(testOrder);

		Order persistedOrder1 = testDao.findById(persistedOrder.getId());
		Assert.isTrue(persistedOrder1.getItems().size() == 3);

		boolean found = false;
		for (OrderItem item : persistedOrder1.getItems()) {
			if (item.getStatus().equals(item3.getStatus())) {
				found = true;
			}
		}
		Assert.isTrue(found);

	}
	
	@Test
	public void testFindByCustomer() {
		Customer customer = createCustomer();
		
		Address deliveryAddress = createAddress();
		deliveryAddress.setCustomer(customer);
		
		Address invoiceAddress = createAddress();
		invoiceAddress.setCustomer(customer);

		customer.setAddress(deliveryAddress);
		
		Order testOrder = createOrder();
		testOrder.setDeliveryAddress(deliveryAddress);
		testOrder.setInvoiceAddress(invoiceAddress);
		testOrder.setCustomer(customer);

		testDao.persist(testOrder);
		
		createdOrders.add(testOrder);
		
		Order o = testDao.findById(testOrder.getId());
		
		Collection<Order> customersOrders = testDao.findByCustomer(o.getCustomer());
		Assert.isTrue(customersOrders.isEmpty() == false);
		
		for(Order order : customersOrders){
			System.out.println("Order ordered " + order.getOrderDate() + ", will be delivered to customer " + order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName()
					+ ", " + order.getDeliveryAddress().getStreet() + ", " + order.getDeliveryAddress().getCity());
		}
		
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
	
	private Order createOrder(){
		Order order = new OrderImpl();
		Date testDate = new Date();
		order.setOrderDate(testDate);
		order.setDiscount(0.05);
		order.setStatus(OrderStatus.IN_PROCESS.toString());
		order.setPaymentTerm(PaymentTerm.DEBIT_ADVICE.toString());
		
		return order;
	}
	
}
