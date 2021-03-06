package de.is.project.shop.impl.persistence;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.domain.Request;
import de.is.project.shop.api.persistence.RequestDAO;
import de.is.project.shop.impl.domain.RequestImpl;

@Repository
@EnableTransactionManagement
public class RequestDAOImpl extends AbstractDAO implements RequestDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Request persist(Request entity) {
		Session session = getCurrentSession();
		session.save(entity);
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Request update(Request entity) {
		Session session = getCurrentSession();
		session.update(entity);
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Request entity) {
		Session session = getCurrentSession();
		session.delete(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Request findById(int id) {
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(RequestImpl.class).add(
				Restrictions.eq("id", id));
		return (RequestImpl) criteria.uniqueResult();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Collection<Request> findByCustomer(Customer customer) {
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(RequestImpl.class).add(
				Restrictions.eq("customer", customer));
		@SuppressWarnings("unchecked")
		List<Request> requests = criteria.list();
		return requests;
	}

	@Override
	public Request getNewInstance() {
		return new RequestImpl();
	}

}
