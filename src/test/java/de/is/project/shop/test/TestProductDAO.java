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
		testProduct.setDescription("Testme");
		testDao.persist(testProduct);

		createdProducts.add(testProduct);

		Product persistedProduct = testDao.findById(testProduct.getId());

		Assert.isTrue(testProduct.getId() > -1);
		Assert.notNull(persistedProduct);
		Assert.isTrue(persistedProduct.getId() == testProduct.getId());
		Assert.isTrue(persistedProduct.getDescription().equals(
				testProduct.getDescription()));
		Assert.isTrue(testProduct == persistedProduct);

	}

	@Test
	public void testFindById() {
		Product testProduct = new ProductImpl();
		testProduct.setDescription("Testme");

		testDao.persist(testProduct);

		createdProducts.add(testProduct);

		Product p = testDao.findById(testProduct.getId());
		Assert.notNull(p);
		Assert.isTrue(p.getId() == testProduct.getId());

	}

	@Test
	public void testDelete() {

		Product testProduct = new ProductImpl();
		testProduct.setDescription("Testme");

		testDao.persist(testProduct);
		createdProducts.add(testProduct);

		int id = testProduct.getId();
		Assert.isTrue(testProduct.getId() > 0);

		testDao.delete(testProduct);
		Product p = testDao.findById(id);
		Assert.isNull(p);

		createdProducts.remove(testProduct);

	}

	@Test
	public void testUpdate() {

		String description1 = "Testme";
		Product testProduct = new ProductImpl();
		testProduct.setDescription(description1);

		testDao.persist(testProduct);
		createdProducts.add(testProduct);

		// initial persist
		Product persistedProduct = testDao.findById(testProduct.getId());
		Assert.isTrue(persistedProduct.getId() == testProduct.getId());
		Assert.isTrue(persistedProduct.getDescription().equals(description1));

		// set new description and update
		String description2 = "New Description";
		persistedProduct.setDescription(description2);

		// update
		testDao.update(persistedProduct);

		Product persistedProduct2 = testDao.findById(persistedProduct.getId());
		Assert.notNull(persistedProduct2);
		Assert.isTrue(persistedProduct2.getDescription().equals(description2));

		createdProducts.remove(testProduct);

	}
}
