package de.is.project.shop.impl.persistence;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;

import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.domain.Order;
import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.persistence.OrderDAO;
import de.is.project.shop.impl.domain.OrderImpl;
import de.is.project.shop.impl.domain.ProductImpl;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Order entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Order> findByCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

}
