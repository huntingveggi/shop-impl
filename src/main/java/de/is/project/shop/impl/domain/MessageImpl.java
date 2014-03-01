package de.is.project.shop.impl.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import de.is.project.shop.api.domain.Message;
import de.is.project.shop.api.domain.Request;

@Entity
@Table(name = "messages")
public class MessageImpl extends AbstractEntity implements Message {

	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = RequestImpl.class)
	Request request;

	Date messageDate;

	@Column
	String text;

	boolean isRead;

	@Column
	boolean isFromAdmin;

	@Override
	@ManyToOne(targetEntity = RequestImpl.class, fetch = FetchType.EAGER)
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
	@Column(name = "isRead")
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

	@Override
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMessageDate() {
		return this.messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

}
