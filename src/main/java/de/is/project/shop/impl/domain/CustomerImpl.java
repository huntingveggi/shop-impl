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
	String activationKey;
	String eMail;
	String password;
	boolean active;
	String role;

	@Override
	@Column(name = "activation_key", unique = true)
	public String getActivationKey() {
		return this.activationKey;
	}

	@Override
	@OneToOne(targetEntity = AddressImpl.class, cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	public Address getAddress() {
		return this.address;
	}

	@Override
	@Column(name = "email", nullable = false, unique=true)
	public String getEMail() {
		return this.eMail;
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
	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	@Override
	@Column(name = "role", nullable = false)
	public String getRole() {
		return role;
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
	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return active;
	}

	@Override
	@Column(name = "billing_customer", nullable = false)
	public boolean isBillingCustomer() {
		return this.billingCustomer;
	}

	@Override
	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	@Override
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public void setBillingCustomer(boolean billingCustomer) {
		this.billingCustomer = billingCustomer;
	}

	@Override
	public void setEMail(String eMail) {
		this.eMail = eMail;
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
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setRole(String role) {
		this.role = role;
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
