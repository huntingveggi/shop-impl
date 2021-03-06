package de.is.project.shop.impl.persistence;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.persistence.CustomerDAO;
import de.is.project.shop.impl.domain.CustomerImpl;
import de.is.project.shop.impl.domain.ProductImpl;

@Repository
@EnableTransactionManagement
public class CustomerDAOImpl extends AbstractDAO implements CustomerDAO{
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Customer persist(Customer customer) {
		Session session = getCurrentSession();
		session.save(customer);
		return customer;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Customer update(Customer customer) {
		Session session = getCurrentSession();
		session.update(customer);
		return customer;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Customer customer) {
		Session session = getCurrentSession();
		session.delete(customer);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Customer findById(int id) {
		Session session = getCurrentSession();
		Customer customer = (Customer) session.byId(CustomerImpl.class).load(id);
		return customer;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Customer findByActivationKey(String activationKey){
		
		return (Customer) getCurrentSession().createCriteria(CustomerImpl.class)
				.add(Restrictions.eq("activationKey", activationKey)).uniqueResult();
	}

	@Override
	public Customer getNewInstance() {
		return new CustomerImpl();
	}

}
