package de.is.project.shop.impl.persistence;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.is.project.shop.api.domain.Category;
import de.is.project.shop.api.domain.Producer;
import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.persistence.ProductDAO;
import de.is.project.shop.impl.domain.ProductImpl;

@Repository
@EnableTransactionManagement
public class ProductDAOImpl extends AbstractDAO implements ProductDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.is.project.shop.api.persistence.ProductDAO#persist(de.is.project.shop
	 * .api.domain.Product)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Product persist(Product product) {
		Session session = getCurrentSession();
		session.saveOrUpdate(product);
		return product;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.is.project.shop.api.persistence.ProductDAO#findById(int)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Product findById(final int id) {
		Product product = (Product) getCurrentSession().get(ProductImpl.class,
				id);
		return product;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.is.project.shop.api.persistence.ProductDAO#findByAttributes(java.util
	 * .HashMap)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Collection<Product> findByAttributes(HashMap<String, Object> map) {
		Session session = getCurrentSession();
		Criteria crit = session.createCriteria(ProductImpl.class);
		Set<Entry<String, Object>> set = map.entrySet();
		for (Entry<String, Object> entry : set) {
			crit.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		return crit.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.is.project.shop.api.persistence.ProductDAO#update(de.is.project.shop
	 * .api.domain.Product)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Product update(Product product) {
		Session session = getCurrentSession();
		session.saveOrUpdate(product);
		return product;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.is.project.shop.api.persistence.ProductDAO#delete(de.is.project.shop
	 * .api.domain.Product)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Product product) {
		Session session = getCurrentSession();
		session.delete(product);
		session.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.is.project.shop.api.persistence.ProductDAO#findByCategories(java.util
	 * .Collection)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Collection<Product> findByCategories(Collection<Category> categories) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.is.project.shop.api.persistence.ProductDAO#findByProducer(de.is.project
	 * .shop.api.domain.Producer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Collection<Product> findByProducer(Producer producer) {
		return getCurrentSession().createCriteria(ProductImpl.class)
				.add(Restrictions.eq("producer", producer)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Collection<Product> findAll() {

		return getCurrentSession().createCriteria(ProductImpl.class).list();
	}

}
