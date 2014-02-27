package de.is.project.shop.impl.domain;

import javax.xml.bind.annotation.XmlRootElement;

import de.is.project.shop.api.domain.Attribute;

@XmlRootElement(name = "attribute")
public class AttributeImpl implements Attribute {

	private String name;
	private String value;

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;

	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;

	}

}
