package de.is.project.shop.test.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import de.is.project.shop.api.domain.Address;
import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.persistence.CustomerDAO;
import de.is.project.shop.api.services.CustomerService;
import de.is.project.shop.impl.domain.AddressImpl;
import de.is.project.shop.impl.domain.CustomerImpl;
import de.is.project.shop.impl.utils.ActivationKeyUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class TestCustomerService {

	@Inject
	private CustomerService customerService;
	
	@Inject
	private CustomerDAO customerDAO;
	
	@Test
	public void testActivateCustomer() {
		
		Customer customer = createCustomer();
		
		customerDAO.persist(customer);
		
		Assert.isTrue(customerService.activateCustomerWithKey(customer.getActivationKey()));
		
	}
	
	@Test
	public void testRegisterCustomer() {
		
		Customer customer = createCustomer();
		Address address = createAddress();
		address.setCustomer(customer);
		customer.setAddress(address);
		
		customerService.setCustomer(customer);
		customerService.registerCustomer();
		
		Assert.isTrue(customer != null);
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
