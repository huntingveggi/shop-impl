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
public class TestAddressDAO {
	
	@Inject
	AddressDAO testDao;
	@Inject
	CustomerDAO customerDAO;
	Collection<Address> createdAddresss = new LinkedList<Address>();
	Collection<Customer> createdCustomers = new LinkedList<Customer>();
	
	@Before
	public void setUp() {	
	}
	
	@After
	public void tearDown(){
		//delete all created Addresses
		for(Address a : createdAddresss){
			Address testA = testDao.findById(a.getId());
			testDao.delete(testA);
		}
		for(Customer c : createdCustomers){
			Customer testC = customerDAO.findById(c.getId());
			customerDAO.delete(testC);
		}
	}
	
	@Test
	public void tetPersistTenProduct(){
		
		Collection<Address> addresses = new LinkedList<Address>();
		
		for(int i = 0; i<10; i++){
			Address testAddress = createAddress();
			testDao.persist(testAddress);
			addresses.add(testAddress);
		}
		
		for(Address address:addresses){
			Address persistedAddress = testDao.findById(address.getId());
			Assert.isTrue(persistedAddress != null);
			Assert.isTrue(persistedAddress.getId()>0);
			createdAddresss.add(persistedAddress);
		}
	}
	
	@Test
	public void testPersist() {
		
		//Initial persist
		Address testAddress = createAddress();
		String testStreet = testAddress.getStreet();
		Customer testCustomer = createCustomer();
		customerDAO.persist(testCustomer);
		createdCustomers.add(testCustomer);
		
		testAddress.setCustomer(testCustomer);
		testDao.persist(testAddress);

		createdAddresss.add(testAddress);

		Address persistedAddress = testDao.findById(testAddress.getId());

		Assert.isTrue(testAddress.getId() > -1);
		Assert.notNull(persistedAddress);
		Assert.isTrue(persistedAddress.getId() == testAddress.getId());
		Assert.isTrue(persistedAddress.getStreet().equals(testStreet));
		Assert.isTrue(persistedAddress.getCustomer().getFirstName().equals(testCustomer.getFirstName()));
	}
	
	@Test
	public void testFindById() {
		Address testAddress = createAddress();
		Customer testCustomer = createCustomer();
		customerDAO.persist(testCustomer);
		createdCustomers.add(testCustomer);
		testAddress.setCustomer(testCustomer);
		
		testDao.persist(testAddress);

		createdAddresss.add(testAddress);

		Address a = testDao.findById(testAddress.getId());
		Assert.notNull(a);
		Assert.isTrue(a.getId() == testAddress.getId());
	}
	
	@Test
	public void testDelete() {

		Address testAddress = createAddress();
		Customer testCustomer = createCustomer();
		customerDAO.persist(testCustomer);
		createdCustomers.add(testCustomer);
		testAddress.setCustomer(testCustomer);
		
		testDao.persist(testAddress);
		createdAddresss.add(testAddress);

		int id = testAddress.getId();
		Assert.isTrue(testAddress.getId() > 0);

		testDao.delete(testAddress);
		Address a = testDao.findById(id);
		Assert.isNull(a);

		createdAddresss.remove(testAddress);

	}
	
	@Test
	public void testUpdate() {

		Address testAddress = createAddress();
		Customer testCustomer = createCustomer();
		customerDAO.persist(testCustomer);
		createdCustomers.add(testCustomer);
		testAddress.setCustomer(testCustomer);
		
		testDao.persist(testAddress);
		createdAddresss.add(testAddress);
		
		// initial persist
		Address persistedAddress = testDao.findById(testAddress.getId());
		Assert.isTrue(persistedAddress.getId() == testAddress.getId());
		Assert.isTrue(persistedAddress.getStreet().equals(testAddress.getStreet()));

		// set new first name and update
		testAddress.setStreet("The Street");

		// update
		testDao.update(testAddress);

		Address persistedAddress2 = testDao.findById(testAddress.getId());
		Assert.notNull(persistedAddress2);
		Assert.isTrue(persistedAddress2.getStreet().equals(testAddress.getStreet()));

	}
	
	@Test
	public void testFindByCustomer() {
		Address testAddress1 = createAddress();
		Address testAddress2 = createAddress();
		testAddress2.setStreet("Street of address 2");
		Customer customer = createCustomer();
		customerDAO.persist(customer);
		createdCustomers.add(customer);
		
		testAddress1.setCustomer(customer);
		testAddress2.setCustomer(customer);

		testDao.persist(testAddress1);
		testDao.persist(testAddress2);
		
		createdAddresss.add(testAddress1);
		createdAddresss.add(testAddress2);
		
		Address a1 = testDao.findById(testAddress1.getId());
		Address a2 = testDao.findById(testAddress2.getId());
		
		Collection<Address> customersAddresses = testDao.findByCustomer(customer);
		Assert.isTrue(customersAddresses.isEmpty() == false);
		
		for(Address address : customersAddresses){
			Assert.isTrue(address.getStreet().equals(a1.getStreet()) || address.getStreet().equals(a2.getStreet()));
		}
		
	}
	
	private Customer createCustomer(){
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
