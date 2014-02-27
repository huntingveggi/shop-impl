package de.is.project.shop.impl.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import de.is.project.shop.api.domain.Category;

@Entity(name = "categories")
@XmlRootElement(name = "category")
public class CategoryImpl extends AbstractEntity implements Category {

	private String name;

	@Override
	@Column(name = "name")
	public String getName() {
		return this.name;

	}

	@Override
	@Transient
	@XmlTransient
	public Collection<Category> getSubCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transient
	@XmlTransient
	public Category getSuperiorCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setSuperiorCategory(Category category) {
		// TODO Auto-generated method stub

	}

}
