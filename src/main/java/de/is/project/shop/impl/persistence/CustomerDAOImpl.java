package de.is.project.shop.impl.persistence;

import java.util.Collection;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.is.project.shop.api.domain.Address;
import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.persistence.CustomerDAO;
import de.is.project.shop.impl.domain.CustomerImpl;

@Repository
@EnableTransactionManagement
public class CustomerDAOImpl extends AbstractDAO implements CustomerDAO{
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Customer persist(Customer customer) {
		Session session = getCurrentSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		session.saveOrUpdate(customer);
		tx.commit();
		return customer;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Customer update(Customer customer) {
		Session session = getCurrentSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		session.update(customer);
		tx.commit();
		return customer;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Customer customer) {
		Session session = getCurrentSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		session.delete(customer);
		tx.commit();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Collection<Address> getCustomersAddresses(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Customer findById(int id) {
		Session session = getCurrentSession();
		Customer customer = (Customer) session.byId(CustomerImpl.class).load(id);
		return customer;
	}

}
