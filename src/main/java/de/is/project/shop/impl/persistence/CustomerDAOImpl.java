package de.is.project.shop.impl.persistence;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;

import de.is.project.shop.api.domain.Address;
import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.persistence.CustomerDAO;
import de.is.project.shop.impl.domain.CustomerImpl;

@Named
@Scope("prototype")
public class CustomerDAOImpl extends AbstractDAO implements CustomerDAO{

	@Inject
	SessionFactory sessionFactory;
	Session session;
	
	@Override
	public Customer persist(Customer customer) {
		Session session = getCurrentSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		session.save(customer);
		tx.commit();
		return customer;
	}

	@Override
	public Customer update(Customer customer) {
		Session session = getCurrentSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		session.update(customer);
		tx.commit();
		return customer;
	}

	@Override
	public void delete(Customer customer) {
		Session session = getCurrentSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		session.delete(customer);
		tx.commit();
	}

	@Override
	public Collection<Address> getCustomersAddresses(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findById(int id) {
		Session session = getCurrentSession();
		Customer customer = (Customer) session.byId(CustomerImpl.class).load(id);
		return customer;
	}

}
