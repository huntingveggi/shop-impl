package de.is.project.shop.impl.persistence;

import java.util.Collection;
import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;

import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.persistence.ProductDAO;

@Named
@Scope("prototype")
public class ProductDAOImpl extends AbstractDAO implements ProductDAO {

	@Inject
	SessionFactory sessionFactory;
	Session session;

	@Override
	public Product persist(Product product) {
		Session session = getCurrentSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		session.save(product);
		tx.commit();
		return product;
	}

	@Override
	public Product findById(int id) {
		Session session = getCurrentSession();
		Product product = (Product) session.get(Product.class, id);
		return product;
	}

	@Override
	public Collection<Product> findByAttributes(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product update(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Product product) {
		Session session = getCurrentSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		session.delete(product);
		tx.commit();
	}

}
