package de.is.project.shop.impl.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.domain.Message;
import de.is.project.shop.api.domain.Request;

@Entity
@Table(name = "messages")
public class MessageImpl extends AbstractEntity implements Message {

	Request request;

	Date messageDate;

	String text;

	boolean isRead;
	
	Customer customer;

	@Override
	@ManyToOne(targetEntity=CustomerImpl.class, cascade=CascadeType.ALL)
	public Customer getCustomer() {
		return this.customer;
	}

	@Override
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMessageDate() {
		return this.messageDate;
	}

	@Override
	@ManyToOne(targetEntity = RequestImpl.class, fetch = FetchType.EAGER)
	public Request getRequest() {
		return request;
	}

	@Override
	@Column(name="text")
	public String getText() {
		return text;
	}

	@Override
	@Column(name = "isRead")
	public boolean isRead() {
		return isRead;
	}

	@Override
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
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
