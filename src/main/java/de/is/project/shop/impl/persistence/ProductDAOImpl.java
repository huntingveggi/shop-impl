package de.is.project.shop.impl.persistence;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;

import de.is.project.shop.api.domain.Category;
import de.is.project.shop.api.domain.Producer;
import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.persistence.ProductDAO;
import de.is.project.shop.impl.domain.ProductImpl;

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
		Product product = (Product) session.byId(ProductImpl.class).load(id);
		return product;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Product> findByAttributes(HashMap<String, Object> map) {
		Session session = getCurrentSession();
		Criteria crit = session.createCriteria(ProductImpl.class);
		Set<Entry<String, Object>> set = map.entrySet();
		for (Entry<String, Object> entry : set) {
			crit.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		return crit.list();
	}

	@Override
	public Product update(Product product) {
		Session session = getCurrentSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		session.update(product);
		tx.commit();
		return product;
	}

	@Override
	public void delete(Product product) {
		Session session = getCurrentSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		session.delete(product);
		tx.commit();
	}

	@Override
	public Collection<Product> findByCategories(Collection<Category> categories) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Product> findByProducer(Producer producer) {
		// TODO Auto-generated method stub
		return null;
	}

}
