package de.is.project.shop.impl.persistence;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.is.project.shop.api.domain.Address;
import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.persistence.AddressDAO;
import de.is.project.shop.api.persistence.CustomerDAO;
import de.is.project.shop.impl.domain.AddressImpl;

@Repository
@EnableTransactionManagement
public class AddressDAOImpl extends AbstractDAO implements AddressDAO {

	@Inject
	CustomerDAO customerDAO;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Address persist(Address entity) {
		Session session = getCurrentSession();
		session.saveOrUpdate(entity);
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Address findById(int id) {
		Session session = getCurrentSession();
		Address address = (Address) session.byId(AddressImpl.class).load(id);
		return address;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Address update(Address entity) {
		Session session = getCurrentSession();
		session.update(entity);
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Address entity) {
		Session session = getCurrentSession();
		if(entity.getCustomer().getAddress() != null){
			if(entity.getCustomer().getAddress().getId() == entity.getId())
			entity.getCustomer().setAddress(null);
			customerDAO.update(entity.getCustomer());
		}
		session.delete(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Collection<Address> findByCustomer(Customer customer) {

		Session session = getCurrentSession();
		String sql = "FROM addresses a WHERE a.customer = :customer";
		Query query = session.createQuery(sql);
		query.setParameter("customer", customer);
		@SuppressWarnings("unchecked")
		List<Address> customersAddresses = query.list();
		return customersAddresses;
	}

	@Override
	public Address getNewInstance() {
		return new AddressImpl();
	}

}
