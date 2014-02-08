package de.is.project.shop.impl.persistence;

import java.util.Collection;
import java.util.HashMap;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.persistence.ProductDAO;

@Component
@Scope("prototype")
public class ProductDAOImpl implements ProductDAO {

	@Inject
	SessionFactory sessionFactory;

	@Override
	public Product persist(Product product) {
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Product p = (Product) session.save(product);
		tx.commit();
		return p;
	}

	@Override
	public Product findById(int id) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub

	}

}
