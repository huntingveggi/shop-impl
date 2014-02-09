package de.is.project.shop.test;

import java.util.Collection;
import java.util.LinkedList;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.persistence.ProductDAO;
import de.is.project.shop.impl.domain.ProductImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class TestProductDAO {

	@Inject
	ProductDAO testDao;
	Collection<Product> createdProducts = new LinkedList<Product>();

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
		for (Product p : createdProducts) {
			Product pTest = testDao.findById(p.getId());
			if (pTest != null) {
				testDao.delete(p);
			}
		}

	}

	@Test
	public void testPersist() {
		Product testProduct = new ProductImpl();
		testDao.persist(testProduct);
		createdProducts.add(testProduct);
		Assert.isTrue(testProduct.getId() > 0);

	}

	@Test
	public void testFindById() {
		Product testProduct = new ProductImpl();
		testDao.persist(testProduct);
		createdProducts.add(testProduct);

		Product p = testDao.findById(testProduct.getId());
		Assert.isTrue(p.getId() == testProduct.getId());

	}

	@Test
	public void testDelete() {
		Product testProduct = new ProductImpl();
		testDao.persist(testProduct);
		createdProducts.add(testProduct);

		int id = testProduct.getId();
		Assert.isTrue(testProduct.getId() > 0);

		testDao.delete(testProduct);
		Product p = testDao.findById(id);
		Assert.isNull(p);
	}
}
