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

import de.is.project.shop.api.domain.Category;
import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.persistence.ProductDAO;
import de.is.project.shop.impl.domain.CategoryImpl;
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

		CategoryImpl category1 = new CategoryImpl();
		category1.setName("Category1");
		CategoryImpl category2 = new CategoryImpl();
		category2.setName("Category2");

		Product testProduct = new ProductImpl();
		testProduct.setDescription("Testme");
		testProduct.getCategories().add(category1);
		testProduct.getCategories().add(category2);

		testDao.persist(testProduct);

		createdProducts.add(testProduct);

		Product persistedProduct = testDao.findById(testProduct.getId());

		Assert.isTrue(testProduct.getId() > -1);
		Assert.notNull(persistedProduct);
		Assert.isTrue(persistedProduct.getId() == testProduct.getId());
		Assert.isTrue(persistedProduct.getDescription().equals(
				testProduct.getDescription()));
		Assert.isTrue(testProduct == persistedProduct);
		Assert.isTrue(persistedProduct.getCategories().size() == 2);

	}

	@Test
	public void testPersistWithAddingCategories() {

		CategoryImpl category1 = new CategoryImpl();
		category1.setName("Category1");
		CategoryImpl category2 = new CategoryImpl();
		category2.setName("Category2");

		Product testProduct = new ProductImpl();
		testProduct.setDescription("Testme");
		testProduct.getCategories().add(category1);
		testProduct.getCategories().add(category2);

		testDao.persist(testProduct);

		createdProducts.add(testProduct);

		Product persistedProduct = testDao.findById(testProduct.getId());

		Assert.isTrue(persistedProduct.getCategories().size() == 2);

		CategoryImpl category3 = new CategoryImpl();
		category3.setName("Category3");
		persistedProduct.getCategories().add(category3);
		testDao.persist(persistedProduct);

		Product persistedProduct3 = testDao.findById(persistedProduct.getId());
		Assert.isTrue(persistedProduct3.getCategories().size() == 3);

		boolean found = false;
		for (Category cat : persistedProduct3.getCategories()) {
			if (cat.getName().equals(category3.getName())) {
				found = true;
			}
		}
		Assert.isTrue(found);

	}

	// @Test
	// public void testPersistWithRemovingCategory() {
	//
	// CategoryImpl category1 = new CategoryImpl();
	// category1.setName("Category1");
	// CategoryImpl category2 = new CategoryImpl();
	// category1.setName("Category2");
	//
	// Product testProduct = new ProductImpl();
	// testProduct.setDescription("Testme");
	// testProduct.getCategories().add(category1);
	// testProduct.getCategories().add(category2);
	//
	// testDao.persist(testProduct);
	//
	// createdProducts.add(testProduct);
	//
	// Product persistedProduct = testDao.findById(testProduct.getId());
	//
	// Assert.isTrue(persistedProduct.getCategories().size() == 2);
	//
	// Category cat = persistedProduct.getCategories().iterator().next();
	// persistedProduct.getCategories().remove(cat);
	// testDao.persist(persistedProduct);
	//
	// Product persistedProduct3 = testDao.findById(persistedProduct.getId());
	// Assert.isTrue(persistedProduct3.getCategories().size() == 2);
	//
	// }

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
