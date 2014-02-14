package de.is.project.shop.impl.persistence;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;

import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.domain.Order;
import de.is.project.shop.api.persistence.OrderDAO;
import de.is.project.shop.impl.domain.OrderImpl;

@Named
@Scope("prototype")
public class OrderDAOImpl extends AbstractDAO implements OrderDAO {

	@Inject
	SessionFactory sessionFactory;
	Session session;
	
	@Override
	public Order persist(Order entity) {
		Session session = getCurrentSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		session.save(entity);
		tx.commit();
		return entity;
	}

	@Override
	public Order findById(int id) {
		Session session = getCurrentSession();
		Order order = (Order) session.byId(OrderImpl.class).load(id);
		return order;
	}

	@Override
	public Order update(Order entity) {
		Session session = getCurrentSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		session.update(entity);
		tx.commit();
		return entity;
	}

	@Override
	public void delete(Order entity) {
		Session session = getCurrentSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();

	}

	@Override
	public Collection<Order> findByCustomer(Customer customer) {
		Session session = getCurrentSession();
		String sql = "FROM orders o WHERE o.customer = :customer";
		Query query = session.createQuery(sql);
		query.setParameter("customer",customer);
		@SuppressWarnings("unchecked")
		List<Order> customersOrders = query.list();
		return customersOrders;
	}
}
