package de.is.project.shop.impl.persistence;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.domain.Order;
import de.is.project.shop.api.persistence.OrderDAO;
import de.is.project.shop.impl.domain.OrderImpl;

@Repository
@EnableTransactionManagement
public class OrderDAOImpl extends AbstractDAO implements OrderDAO {
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Order persist(Order entity) {
		Session session = getCurrentSession();
		session.save(entity);
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Order findById(int id) {
		Session session = getCurrentSession();
		Order order = (Order) session.byId(OrderImpl.class).load(id);
		return order;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Order update(Order entity) {
		Session session = getCurrentSession();
		session.update(entity);
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Order entity) {
		Session session = getCurrentSession();
		session.delete(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
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
