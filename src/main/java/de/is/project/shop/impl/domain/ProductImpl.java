package de.is.project.shop.impl.domain;

import java.io.InputStream;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import de.is.project.shop.api.domain.Attribute;
import de.is.project.shop.api.domain.Category;
import de.is.project.shop.api.domain.Producer;
import de.is.project.shop.api.domain.Product;

@Entity(name = "products")
public class ProductImpl extends AbstractEntity implements Product {

	Producer producer;
	private Collection<Category> categories;
	private Collection<Attribute> attributes;
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
	@OneToMany(targetEntity = CategoryImpl.class, cascade = { CascadeType.ALL })
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
	@OneToOne(targetEntity = ProducerImpl.class, cascade = { CascadeType.ALL })
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
