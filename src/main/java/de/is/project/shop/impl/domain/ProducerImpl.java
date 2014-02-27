package de.is.project.shop.impl.domain;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import de.is.project.shop.api.domain.Producer;

@Entity(name = "producers")
@XmlRootElement(name = "producer")
public class ProducerImpl extends AbstractEntity implements Producer {

	private String name;

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
