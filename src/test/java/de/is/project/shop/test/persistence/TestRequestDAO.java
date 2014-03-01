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
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import de.is.project.shop.api.domain.Address;
import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.domain.Documentation;
import de.is.project.shop.api.domain.Message;
import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.domain.Request;
import de.is.project.shop.api.persistence.CustomerDAO;
import de.is.project.shop.api.persistence.RequestDAO;
import de.is.project.shop.impl.domain.AddressImpl;
import de.is.project.shop.impl.domain.CategoryImpl;
import de.is.project.shop.impl.domain.CustomerImpl;
import de.is.project.shop.impl.domain.DocumentationImpl;
import de.is.project.shop.impl.domain.MessageImpl;
import de.is.project.shop.impl.domain.ProductImpl;
import de.is.project.shop.impl.domain.RequestImpl;
import de.is.project.shop.impl.domain.RequestStatus;
import de.is.project.shop.impl.utils.ActivationKeyUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring.xml" })
@TransactionConfiguration(defaultRollback = true)
public class TestRequestDAO {

	@Inject
	RequestDAO testDao;
	@Inject
	CustomerDAO customerDAO;

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
	public void tetPersistTenProducts(){
		
		Collection<Request> requests = new LinkedList<Request>();
		
		for(int i = 0; i<10; i++){
			Request testRequest = createRequest();
			testDao.persist(testRequest);
			requests.add(testRequest);
		}
		
		for(Request request:requests){
			Request persistedRequest = testDao.findById(request.getId());
			Assert.isTrue(persistedRequest != null);
			Assert.isTrue(persistedRequest.getId()>0);
			createdRequests.add(persistedRequest);
		}
	}

	@Test
	public void testPersist() {

		Request testRequest = createRequest();
		testDao.persist(testRequest);

		createdRequests.add(testRequest);

		Request persistedRequest = testDao.findById(testRequest.getId());

		Assert.isTrue(testRequest.getId() > -1);
		Assert.notNull(persistedRequest);
		Assert.isTrue(persistedRequest.getId() == testRequest.getId());

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
	
	@Test
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void testPersistWithAddingMessages() {

		Customer customer = createCustomer();
		customerDAO.persist(customer);
		
		Request testRequest = createRequest();
		testRequest.setCustomer(customer);
		testDao.persist(testRequest);
		
		Message message=new MessageImpl();
		message.setText("Hallo");
		message.setRead(false);
		message.setCustomer(customer);
		message.setRequest(testRequest);
		
		testRequest.getMessages().add(message);
		
		testDao.update(testRequest);

		createdRequests.add(testRequest);

		Request persistedRequest = testDao.findById(testRequest.getId());

		Assert.isTrue(testRequest.getId() > -1);
		Assert.notNull(persistedRequest);
		Assert.isTrue(persistedRequest.getId() == testRequest.getId());
		
		Assert.isTrue(getMessages(persistedRequest).size()==1);
		
		for (Message theMessage : persistedRequest.getMessages()){
			Assert.isTrue(theMessage.getText().equals(message.getText()));
		}

	}
	
	@Test
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void testPersistWithAddingMessagesAndDocumentations() {

		Customer customer = createCustomer();
		customerDAO.persist(customer);
		
		Request testRequest = createRequest();
		testRequest.setCustomer(customer);
		testDao.persist(testRequest);
		
		Message message=new MessageImpl();
		message.setText("Hallo");
		message.setRead(false);
		message.setCustomer(customer);
		message.setRequest(testRequest);
		
		Documentation documentation = new DocumentationImpl();
		documentation.setDescription("Description");
		documentation.setName("Name");
		documentation.setRequest(testRequest);
		
		testRequest.getMessages().add(message);
		testRequest.getDocumentations().add(documentation);
		
		testDao.update(testRequest);

		createdRequests.add(testRequest);

		Request persistedRequest = testDao.findById(testRequest.getId());

		Assert.isTrue(testRequest.getId() > -1);
		Assert.notNull(persistedRequest);
		Assert.isTrue(persistedRequest.getId() == testRequest.getId());
		
		Assert.isTrue(persistedRequest.getMessages().size()==1);
		
		for (Message theMessage : persistedRequest.getMessages()){
			Assert.isTrue(theMessage.getText().equals(message.getText()));
		}
		
		Assert.isTrue(persistedRequest.getDocumentations().size()==1);
		
		for (Documentation theDocu : persistedRequest.getDocumentations()){
			Assert.isTrue(theDocu.getDescription().equals(documentation.getDescription()));
		}

	}

	private Collection<Message> getMessages(Request request){
		Collection<Message> messages = testDao.findById(request.getId()).getMessages();
		messages.size();
		return messages;
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

	private Request createRequest() {
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
