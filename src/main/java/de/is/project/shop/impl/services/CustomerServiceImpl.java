package de.is.project.shop.impl.services;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.persistence.CustomerDAO;
import de.is.project.shop.api.services.CustomerService;

@Named
@Scope("prototype")
public class CustomerServiceImpl implements CustomerService {

	@Inject
	CustomerDAO customerDAO;
	Customer customer;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean activateCustomerWithKey(String activationKey) {
		
		Customer customer = customerDAO.findByActivationKey(activationKey);
		if(customer == null){
			return false;
		}else{
			customer.setActivationKey("");
			customer.setActive(true);
			return true;
		}
	}

	@Override
	public void registerCustomer() {
		if(this.customer==null){
			RuntimeException ex = new RuntimeException("Error registering customer. Customer cannot be null. Use setCustomer method befor registering.");
			throw ex;
		}else{
			
		}
	}

	@Override
	public void setCustomer(Customer customer) {
		if(this.customer == null){
			this.customer=customer;
		}
		
	}

}
