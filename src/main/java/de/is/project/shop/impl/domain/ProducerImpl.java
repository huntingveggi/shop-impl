package de.is.project.shop.impl.domain;

import javax.persistence.Entity;

import de.is.project.shop.api.domain.Producer;

@Entity(name = "producer")
public class ProducerImpl extends AbstractEntity implements Producer {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

}
