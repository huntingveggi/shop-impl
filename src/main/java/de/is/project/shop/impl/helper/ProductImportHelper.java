package de.is.project.shop.impl.helper;

import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import de.is.project.shop.impl.domain.AttributeImpl;
import de.is.project.shop.impl.domain.CategoryImpl;
import de.is.project.shop.impl.domain.ProducerImpl;
import de.is.project.shop.impl.domain.ProductImpl;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductImportHelper {

	@XmlElement(name = "product")
	List<ProductImpl> products = new LinkedList<ProductImpl>();

	public static void main(String[] args) {
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
		JAXB.marshal(helper, writer);

		System.out.println(writer.getBuffer().toString());
	}

	public List<ProductImpl> getProducts() {
		return products;
	}

	public void setProducts(List<ProductImpl> products) {
		this.products = products;
	}

}
