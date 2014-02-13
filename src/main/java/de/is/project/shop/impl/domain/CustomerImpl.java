package de.is.project.shop.impl.domain;

import javax.persistence.Column;

import de.is.project.shop.api.domain.Customer;



public class CustomerImpl extends AbstractEntity implements Customer {

	String title;
	String firstName;
	String lastName;
	String sex;
	boolean billingCustomer;

	@Override
	@Column(name = "Title", nullable = true)
	public String getTitle() {
		return this.title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	@Column(name = "FirstName", nullable = false)
	public String getFirstName() {
		return this.firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	@Column(name = "LastName", nullable = false)
	public String getLastName() {
		return this.lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	@Column(name = "Sex", nullable = false)
	public String getSex() {
		return this.sex;
	}

	@Override
	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	@Column(name = "BillingCustomer", nullable = false)
	public boolean isBillingCustomer() {
		return this.billingCustomer;
	}

	@Override
	public void setBillingCustomer(boolean billingCustomer) {
		this.billingCustomer = billingCustomer;
	}

}
