package de.is.project.shop.impl.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import de.is.project.shop.api.domain.Address;
import de.is.project.shop.api.domain.Customer;

@Entity(name = "customers")
public class CustomerImpl extends AbstractEntity implements Customer {

	String title;
	String firstName;
	String lastName;
	String sex;
	Address address;
	boolean billingCustomer;

	@Override
	@OneToOne(targetEntity=AddressImpl.class, cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	public Address getAddress() {
		return this.address;
	}

	@Override
	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return this.firstName;
	}

	@Override
	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return this.lastName;
	}

	@Override
	@Column(name = "sex", nullable = false)
	public String getSex() {
		return this.sex;
	}

	@Override
	@Column(name = "title", nullable = true)
	public String getTitle() {
		return this.title;
	}

	@Override
	@Column(name = "billing_customer", nullable = false)
	public boolean isBillingCustomer() {
		return this.billingCustomer;
	}

	@Override
	public void setAddress(Address address) {
		this.address=address;
	}

	@Override
	public void setBillingCustomer(boolean billingCustomer) {
		this.billingCustomer = billingCustomer;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

}
