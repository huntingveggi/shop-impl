package de.is.project.shop.impl.domain;

import java.util.Collection;

import javax.persistence.Entity;

import de.is.project.shop.api.domain.Attribute;
import de.is.project.shop.api.domain.Category;
import de.is.project.shop.api.domain.Producer;
import de.is.project.shop.api.domain.Product;

@Entity(name = "product")
public class ProductImpl implements Product {

	@Override
	public Producer getProducer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Category> getCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Attribute> getaAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

}
