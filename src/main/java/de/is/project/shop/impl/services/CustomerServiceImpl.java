package de.is.project.shop.impl.services;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.persistence.CustomerDAO;
import de.is.project.shop.api.services.CustomerService;

@Named
@Scope("prototype")
public class CustomerServiceImpl implements CustomerService {

	@Inject
	CustomerDAO customerDAO;
	
	@Override
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
		// TODO Auto-generated method stub

	}

}
