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
	public boolean getIsFromAdmin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getIsRead() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@ManyToOne(targetEntity=RequestImpl.class)
	public Request getRequest() {
		return request;
	}

	@Override
	public String getText() {
		return text;
	}

	public boolean isFromAdmin() {
		return isFromAdmin;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setFromAdmin(boolean isFromAdmin) {
		this.isFromAdmin = isFromAdmin;
	}

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
