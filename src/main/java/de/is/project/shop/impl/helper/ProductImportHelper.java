package de.is.project.shop.impl.helper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.persistence.ProductDAO;
import de.is.project.shop.impl.domain.AttributeImpl;
import de.is.project.shop.impl.domain.CategoryImpl;
import de.is.project.shop.impl.domain.ProducerImpl;
import de.is.project.shop.impl.domain.ProductImpl;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
@Named
public class ProductImportHelper {

	@XmlElement(name = "product")
	List<ProductImpl> products = new LinkedList<ProductImpl>();

	@Inject
	@XmlTransient
	ApplicationContext context;

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath*:**/spring.xml");

		ProductImportHelper helper = context.getBean(ProductImportHelper.class);

		helper.start();
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void start() {

		ProductDAO productDAO = context.getBean(ProductDAO.class);

		ProductImpl productImpl = new ProductImpl();
		productImpl.setDescription("desc");
		productImpl.setMeasurand("measruer");
		productImpl.setName("name");
		productImpl.setPrice(1.2);
		productImpl.setRateOfTaxation(1.2);
		productImpl.setSpecialOffer(true);
		productImpl.setStock(100);

		CategoryImpl categoryImpl = new CategoryImpl();
		categoryImpl.setName("name");
		productImpl.getCategories().add(categoryImpl);

		AttributeImpl attributeImpl = new AttributeImpl();
		attributeImpl.setName("name");
		attributeImpl.setValue("value");
		productImpl.getAttributes().add(attributeImpl);

		ProducerImpl producerImpl = new ProducerImpl();
		producerImpl.setName("name");
		productImpl.setProducer(producerImpl);

		ProductImportHelper helper = new ProductImportHelper();
		helper.getProducts().add(productImpl);

		StringWriter writer = new StringWriter();
		// JAXB.marshal(helper, writer);

		ClassPathResource resource = new ClassPathResource(
				"/sample-product.xml");

		try {
			ProductImportHelper importHelper = JAXB.unmarshal(
					resource.getInputStream(), ProductImportHelper.class);
			for (Product p : importHelper.getProducts()) {
				System.out.println(p);
				productDAO.persist(p);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(writer.getBuffer().toString());

	}

	public List<ProductImpl> getProducts() {
		return products;
	}

	public void setProducts(List<ProductImpl> products) {
		this.products = products;
	}

}
