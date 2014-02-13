package de.is.project.shop.impl.domain;

import java.io.InputStream;
import java.util.Collection;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.context.annotation.Scope;

import de.is.project.shop.api.domain.Attribute;
import de.is.project.shop.api.domain.Category;
import de.is.project.shop.api.domain.Producer;
import de.is.project.shop.api.domain.Product;

@Named
@Scope("prototype")
@Entity(name = "product")
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
	@Transient
	public Collection<Category> getCategories() {
		return this.categories;
	}

	@Override
	@OneToOne(targetEntity = ProducerImpl.class)
	public Producer getProducer() {
		return this.producer;
	}

	public void setAttributes(Collection<Attribute> attributes) {
		this.attributes = attributes;
	}

	public void setCategories(Collection<Category> categories) {
		this.categories = categories;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;

	}

	@Override
	public double getPrice() {
		return this.price;
	}

	@Override
	public void setPrice(double price) {
		this.price = price;

	}

	@Override
	public int getStock() {
		return this.stock;
	}

	@Override
	public void setStock(int stock) {
		this.stock = stock;

	}

	@Override
	public InputStream getPicture() {
		return this.picture;
	}

	@Override
	public void setPicture(InputStream picture) {
		this.picture = picture;
	}

	@Override
	public double getRateOfTaxation() {
		return this.rateOfTaxation;
	}

	@Override
	public void setRateOfTaxation(double rateOfTaxation) {
		this.rateOfTaxation = rateOfTaxation;
	}

	@Override
	public String getMeasurand() {
		return this.measurand;
	}

	@Override
	public void setMeasurand(String measurand) {
		this.measurand = measurand;
	}

	public boolean isSpecialOffer() {
		return isSpecialOffer;
	}

	public void setSpecialOffer(boolean isSpecialOffer) {
		this.isSpecialOffer = isSpecialOffer;
	}

}
