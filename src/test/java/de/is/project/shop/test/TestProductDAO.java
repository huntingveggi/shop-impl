package de.is.project.shop.test;

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

	Product testProduct;

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {
		if (testProduct != null) {
			testDao.delete(testProduct);
		}

	}

	@Test
	public void testPersist() {
		testProduct = new ProductImpl();
		testDao.persist(testProduct);
		Assert.isTrue(testProduct.getId() > 0);
	}

}
