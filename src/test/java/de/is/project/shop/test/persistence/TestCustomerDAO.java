package de.is.project.shop.test.persistence;

import java.util.Collection;
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
import de.is.project.shop.api.persistence.AddressDAO;
import de.is.project.shop.api.persistence.CustomerDAO;
import de.is.project.shop.impl.domain.AddressImpl;
import de.is.project.shop.impl.domain.CustomerImpl;
import de.is.project.shop.impl.utils.ActivationKeyUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
@TransactionConfiguration(defaultRollback = true)
public class TestCustomerDAO {

	@Inject
	CustomerDAO testDao;
	@Inject
	AddressDAO addressDAO;
	Collection<Customer> createdCustomers = new LinkedList<Customer>();
	Collection<Address> createdAddresses = new LinkedList<Address>();

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
		// delete all created customers
		for (Address a : createdAddresses) {
			Address testA = addressDAO.findById(a.getId());
			addressDAO.delete(testA);
		}
		for (Customer c : createdCustomers) {
			Customer testC = testDao.findById(c.getId());
			testDao.delete(testC);
		}
	}

	@Test
	public void testPersist() {

		// Initial persist
		Customer testCustomer = createCustomer();
		String testFirstName = testCustomer.getFirstName();
		Address testAddress = createAddress();
		testAddress.setCustomer(testCustomer);
		testDao.persist(testCustomer);
		addressDAO.persist(testAddress);
		createdAddresses.add(testAddress);

		createdCustomers.add(testCustomer);

		Customer persistedCustomer = testDao.findById(testCustomer.getId());

		Assert.isTrue(testCustomer.getId() > -1);
		Assert.notNull(persistedCustomer);
		Assert.isTrue(persistedCustomer.getId() == testCustomer.getId());
		Assert.isTrue(persistedCustomer.getFirstName().equals(testFirstName));

		testCustomer.setAddress(testAddress);

		// Update Address
		testDao.update(testCustomer);
		persistedCustomer = testDao.findById(testCustomer.getId());
		Assert.isTrue(persistedCustomer.getAddress().getId() == testCustomer
				.getAddress().getId());
	}

	@Test
	public void testFindById() {
		Customer testCustomer = createCustomer();

		testDao.persist(testCustomer);

		createdCustomers.add(testCustomer);

		Customer o = testDao.findById(testCustomer.getId());
		Assert.notNull(o);
		Assert.isTrue(o.getId() == testCustomer.getId());
	}

	@Test
	public void testDelete() {

		Customer testCustomer = createCustomer();

		testDao.persist(testCustomer);
		createdCustomers.add(testCustomer);

		int id = testCustomer.getId();
		Assert.isTrue(testCustomer.getId() > 0);

		testDao.delete(testCustomer);
		Customer o = testDao.findById(id);
		Assert.isNull(o);

		createdCustomers.remove(testCustomer);

	}

	@Test
	public void testUpdate() {

		Customer testCustomer = createCustomer();

		testDao.persist(testCustomer);
		createdCustomers.add(testCustomer);

		// initial persist
		Customer persistedCustomer = testDao.findById(testCustomer.getId());
		Assert.isTrue(persistedCustomer.getId() == testCustomer.getId());
		Assert.isTrue(persistedCustomer.getFirstName().equals(
				testCustomer.getFirstName()));

		// set new first name and update
		testCustomer.setFirstName("Ralf");

		// update
		testDao.update(testCustomer);

		Customer persistedCustomer2 = testDao.findById(testCustomer.getId());
		Assert.notNull(persistedCustomer2);
		Assert.isTrue(persistedCustomer2.getFirstName().equals(
				testCustomer.getFirstName()));

	}

	@Test
	public void testFindByActivationKey() {
		Customer customer = createCustomer();
		String key = customer.getActivationKey();
		customer.setActivationKey(key);
		testDao.persist(customer);

		Customer persistedCustomer = testDao.findByActivationKey(customer
				.getActivationKey());
		Assert.isTrue(persistedCustomer != null);
	}

	private Customer createCustomer() {
		Customer customer = new CustomerImpl();
		customer.setFirstName("Max");
		customer.setLastName("Mustermann");
		customer.setSex("male");
		customer.setTitle("Dr.");
		customer.setActivationKey(ActivationKeyUtil.getUniqueActivationKey());
		customer.setEMail("lala@lulu.de");
		customer.setPassword("geheim;-)");
		customer.setActive(false);
		customer.setBillingCustomer(false);

		return customer;
	}

	private Address createAddress() {
		Address address = new AddressImpl();
		address.setStreet("Street");
		address.setStreetNumber("1b");
		address.setZipCode("12345");
		address.setCountry("Germany");
		address.setCity("City");
		return address;
	}

}
