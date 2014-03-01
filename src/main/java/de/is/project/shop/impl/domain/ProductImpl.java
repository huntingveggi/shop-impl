package de.is.project.shop.impl.domain;

import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import de.is.project.shop.api.domain.Attribute;
import de.is.project.shop.api.domain.Category;
import de.is.project.shop.api.domain.Producer;
import de.is.project.shop.api.domain.Product;

@Entity(name = "products")
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductImpl extends AbstractEntity implements Product {

	@XmlElement(name = "producer", type = ProducerImpl.class)
	private Producer producer;

	@XmlElementWrapper(name = "categories")
	@XmlElement(name = "category", type = CategoryImpl.class)
	private Collection<Category> categories = new LinkedList<Category>();

	@XmlElementWrapper(name = "attributes")
	@XmlElement(name = "attribute", type = AttributeImpl.class)
	private Collection<Attribute> attributes = new LinkedList<Attribute>();

	private String name;
	private String description;
	private double price;
	private int stock;
	private InputStream picture;
	private double rateOfTaxation;
	private String measurand;
	private boolean isSpecialOffer;

	@Override
	@Transient
	public Collection<Attribute> getAttributes() {
		return this.attributes;
	}

	@Override
	@ManyToMany(targetEntity = CategoryImpl.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	public Collection<Category> getCategories() {
		return this.categories;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public String getMeasurand() {
		return this.measurand;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	@Transient
	public InputStream getPicture() {
		return this.picture;
	}

	@Override
	public double getPrice() {
		return this.price;
	}

	@Override
	@ManyToOne(targetEntity = ProducerImpl.class, cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	public Producer getProducer() {
		return this.producer;
	}

	@Override
	public double getRateOfTaxation() {
		return this.rateOfTaxation;
	}

	@Override
	public int getStock() {
		return this.stock;
	}

	@Override
	public boolean isSpecialOffer() {
		return isSpecialOffer;
	}

	public void setAttributes(Collection<Attribute> attributes) {
		this.attributes = attributes;
	}

	public void setCategories(Collection<Category> categories) {
		this.categories = categories;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;

	}

	@Override
	public void setMeasurand(String measurand) {
		this.measurand = measurand;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setPicture(InputStream picture) {
		this.picture = picture;
	}

	@Override
	public void setPrice(double price) {
		this.price = price;

	}

	@Override
	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	@Override
	public void setRateOfTaxation(double rateOfTaxation) {
		this.rateOfTaxation = rateOfTaxation;
	}

	@Override
	public void setSpecialOffer(boolean isSpecialOffer) {
		this.isSpecialOffer = isSpecialOffer;
	}

	@Override
	public void setStock(int stock) {
		this.stock = stock;

	}

}
