package de.is.project.shop.test;

import java.util.Collection;
import java.util.LinkedList;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import de.is.project.shop.api.domain.Address;
import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.persistence.CustomerDAO;
import de.is.project.shop.impl.domain.AddressImpl;
import de.is.project.shop.impl.domain.CustomerImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class TestCustomerDAO {
	
	@Inject
	CustomerDAO testDao;
	Collection<Customer> createdCustomer = new LinkedList<Customer>();
	
	
	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
		for (Customer c : createdCustomer) {
			Customer cTest = testDao.findById(c.getId());
			if (cTest != null) {
				testDao.delete(c);
			}
		}
	}
	
	@Test
	public void testPersist() {

		Customer testCustomer = createCustomer();
		Address address = createAddress();
		testCustomer.setAddress(address);
		testDao.persist(testCustomer);

		createdCustomer.add(testCustomer);

		Customer persistedCustomer = testDao.findById(testCustomer.getId());

		Assert.isTrue(testCustomer.getId() > -1);
		Assert.notNull(persistedCustomer);
		Assert.isTrue(persistedCustomer.getId() == testCustomer.getId());
		//Assert.isTrue(persistedCustomer.getAddress().getStreet().equals(address.getStreet()));
		Assert.isTrue(testCustomer == persistedCustomer);

	}

	@Test
	public void testFindById() {
		Customer testCustomer = createCustomer();

		testDao.persist(testCustomer);

		createdCustomer.add(testCustomer);

		Customer o = testDao.findById(testCustomer.getId());
		Assert.notNull(o);
		Assert.isTrue(o.getId() == testCustomer.getId());
	}
	
	@Test
	public void testDelete() {

		Customer testCustomer = createCustomer();

		testDao.persist(testCustomer);
		createdCustomer.add(testCustomer);

		int id = testCustomer.getId();
		Assert.isTrue(testCustomer.getId() > 0);

		testDao.delete(testCustomer);
		Customer o = testDao.findById(id);
		Assert.isNull(o);

		createdCustomer.remove(testCustomer);

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

}
