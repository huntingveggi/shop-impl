package de.is.project.shop.impl.domain;

import java.io.InputStream;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import de.is.project.shop.api.domain.Documentation;
import de.is.project.shop.api.domain.Request;

public class DocumentationImpl extends AbstractEntity implements Documentation {

	Request request;
	String name;
	String description;
	InputStream picture;

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	@Transient
	public InputStream getPicture() {
		return picture;
	}

	@Override
	@ManyToOne(targetEntity=RequestImpl.class, fetch = FetchType.EAGER)
	public Request getRequest() {
		return request;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
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
	public void setRequest(Request request) {
		this.request = request;
	}

}
