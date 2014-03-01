package de.is.project.shop.impl.domain;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import de.is.project.shop.api.domain.Address;
import de.is.project.shop.api.domain.Customer;
import de.is.project.shop.api.domain.Documentation;
import de.is.project.shop.api.domain.Message;
import de.is.project.shop.api.domain.Request;
import de.is.project.shop.api.domain.RequestItem;
import de.is.project.shop.api.domain.Visitable;
import de.is.project.shop.api.domain.Visitor;

@Entity
@Table(name = "requests")
public class RequestImpl extends AbstractEntity implements Request, Visitable {

	Customer customer;
	Collection<RequestItem> items = new LinkedList<RequestItem>();
	Collection<Message> messages = new LinkedList<Message>();
	Collection<Documentation> documentations = new LinkedList<Documentation>();
	Date requestDate;
	Date deliveryDate;
	Address deliveryAddress;
	double discount;
	String status;
	double total;

	@Override
	@ManyToOne(targetEntity = CustomerImpl.class, fetch = FetchType.EAGER)
	public Customer getCustomer() {
		return customer;
	}

	@Override
	@ManyToOne(targetEntity = AddressImpl.class, fetch = FetchType.EAGER)
	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	@Override
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	@Override
	@Column
	public double getDiscount() {
		return discount;
	}

	@Override
	@OneToMany(targetEntity = DocumentationImpl.class, cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name="request_id")
	public Collection<Documentation> getDocumentations() {
		return documentations;
	}

	@Override
	@OneToMany(targetEntity = RequestItemImpl.class, cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name="request_id")
	public Collection<RequestItem> getItems() {
		return items;
	}

	@Override
	@OneToMany(targetEntity = MessageImpl.class, cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name="request_id")
	public Collection<Message> getMessages() {
		return messages;
	}

	@Override
	@Temporal(TemporalType.TIMESTAMP)
	public Date getRequestDate() {
		return requestDate;
	}

	@Override
	@Column
	public String getStatus() {
		return status;
	}

	@Override
	public double getTotal() {
		return total;
	}

	@Override
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	@Override
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Override
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public void setDocumentations(Collection<Documentation> documentations) {
		this.documentations = documentations;
	}

	public void setItems(Collection<RequestItem> items) {
		this.items = items;
	}

	public void setMessages(Collection<Message> messages) {
		this.messages = messages;
	}

	@Override
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);		
	}

}
