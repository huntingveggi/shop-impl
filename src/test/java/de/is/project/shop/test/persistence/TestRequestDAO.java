package de.is.project.shop.test.persistence;

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
import org.springframework.util.Assert;

import de.is.project.shop.api.domain.Address;
import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.domain.Request;
import de.is.project.shop.api.domain.Request;
import de.is.project.shop.api.persistence.RequestDAO;
import de.is.project.shop.impl.domain.AddressImpl;
import de.is.project.shop.impl.domain.CustomerImpl;
import de.is.project.shop.impl.domain.RequestImpl;
import de.is.project.shop.impl.domain.RequestStatus;
import de.is.project.shop.impl.domain.PaymentTerm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class TestRequestDAO {
	
	@Inject
	RequestDAO testDao;
	Collection<Request> createdRequests = new LinkedList<Request>();
	
	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
		for (Request r : createdRequests) {
			Request oTest = testDao.findById(r.getId());
			if (oTest != null) {
				testDao.delete(r);
			}
		}
	}
	
	@Test
	public void testPersist() {

		Request testRequest = createRequest();
		Date testDate = testRequest.getRequestDate();
		testDao.persist(testRequest);

		createdRequests.add(testRequest);

		Request persistedRequest = testDao.findById(testRequest.getId());

		Assert.isTrue(testRequest.getId() > -1);
		Assert.notNull(persistedRequest);
		Assert.isTrue(persistedRequest.getId() == testRequest.getId());
		Assert.isTrue(persistedRequest.getRequestDate().equals(testDate));
		Assert.isTrue(testRequest == persistedRequest);

	}

	@Test
	public void testFindById() {
		Request testRequest = createRequest();

		testDao.persist(testRequest);

		createdRequests.add(testRequest);

		Request o = testDao.findById(testRequest.getId());
		Assert.notNull(o);
		Assert.isTrue(o.getId() == testRequest.getId());
	}
	
	@Test
	public void testDelete() {

		Request testRequest = createRequest();

		testDao.persist(testRequest);
		createdRequests.add(testRequest);

		int id = testRequest.getId();
		Assert.isTrue(testRequest.getId() > 0);

		testDao.delete(testRequest);
		Request o = testDao.findById(id);
		Assert.isNull(o);

		createdRequests.remove(testRequest);

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
	
	private Request createRequest(){
		Request request = new RequestImpl();
		Date testDate = new Date();
		request.setRequestDate(testDate);
		request.setDiscount(0.05);
		request.setStatus(RequestStatus.IN_PROCESS.toString());
		request.setTotal(0);
		request.setDeliveryDate(new Date());
		return request;
	}

}
