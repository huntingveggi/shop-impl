package de.is.project.shop.impl.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import de.is.project.shop.api.domain.Attribute;
import de.is.project.shop.api.domain.Category;
import de.is.project.shop.api.domain.Producer;
import de.is.project.shop.api.domain.Product;

@Entity(name = "product")
public class ProductImpl extends AbstractEntity implements Product {

	Producer producer;
	private Collection<Category> categories;
	private Collection<Attribute> attributes;

	@Override
	@Transient
	public Collection<Attribute> getaAttributes() {
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

}
