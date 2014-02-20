package de.is.project.shop.impl.domain;

import javax.persistence.ManyToOne;

import de.is.project.shop.api.domain.Message;
import de.is.project.shop.api.domain.Request;

public class MessageImpl extends AbstractEntity implements Message {

	Request request;
	String text;
	boolean isRead;
	boolean isFromAdmin;

	@Override
	@ManyToOne(targetEntity = RequestImpl.class)
	public Request getRequest() {
		return request;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public boolean isFromAdmin() {
		return isFromAdmin;
	}

	@Override
	public boolean isRead() {
		return isRead;
	}

	@Override
	public void setFromAdmin(boolean isFromAdmin) {
		this.isFromAdmin = isFromAdmin;
	}

	@Override
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	@Override
	public void setRequest(Request request) {
		this.request = request;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}

}
